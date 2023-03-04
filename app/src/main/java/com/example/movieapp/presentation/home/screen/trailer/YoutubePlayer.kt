package com.example.movieapp.presentation.home.screen.trailer

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController

@Composable
fun YoutubePlayer(
    videoId: String,
    modifier: Modifier,
    rotation: MutableState<YoutubePlayerRotation>,
    rotationChanged: (YoutubePlayerRotation) -> Unit
) {
    val viewState = remember {
        mutableStateOf<YouTubePlayerView?>(null)
    }

    LaunchedEffect(rotation.value) {
        viewState.value?.let { player ->
            if (rotation.value == YoutubePlayerRotation.DEFAULT) {
                player.exitFullScreen()
                rotation.value = YoutubePlayerRotation.DEFAULT
            }
            if (rotation.value == YoutubePlayerRotation.FULLSCREEN) {
                player.enterFullScreen()
                rotation.value = YoutubePlayerRotation.FULLSCREEN
            }
        }
    }

    if (videoId.isNotEmpty()) {
        AndroidView(
            factory = {
                val view = YouTubePlayerView(it)
                view.enableAutomaticInitialization = false

                val playerListener = object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        val defaultPlayerUiController =
                            DefaultPlayerUiController(view, youTubePlayer)
                        view.setCustomPlayerUi(defaultPlayerUiController.rootView)
                        youTubePlayer.cueVideo(videoId, 0f)

                    }
                }
                val fullScreenListener = object : YouTubePlayerFullScreenListener {
                    override fun onYouTubePlayerEnterFullScreen() {
                        rotationChanged.invoke(YoutubePlayerRotation.FULLSCREEN)
                    }

                    override fun onYouTubePlayerExitFullScreen() {
                        rotationChanged.invoke(YoutubePlayerRotation.DEFAULT)
                    }

                }
                view.addYouTubePlayerListener(playerListener)
                view.addFullScreenListener(fullScreenListener)

                val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
                view.initialize(playerListener, options)
                viewState.value = view
                return@AndroidView view
            },
            modifier = modifier
        )
    }
}

enum class YoutubePlayerRotation {
    DEFAULT,
    FULLSCREEN
}