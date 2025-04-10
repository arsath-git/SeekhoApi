package com.example.seekhoapi.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import com.example.seekhoapi.ui.theme.PurpleGrey40
import com.example.seekhoapi.viewModel.DetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun DetailScreen(animeId: Int, viewModel: DetailViewModel = DetailViewModel(), paddingValues: PaddingValues) {
    val detail by viewModel.animeDetail.collectAsState()

    LaunchedEffect(animeId) {
        viewModel.fetchAnime(animeId)
    }

    if(detail==null) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(PurpleGrey40)
            ) {
                Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
                    YouTubeScreen(videoUrl = detail!!.trailer.youtube_id.toString(), posterUrl = detail!!.images.jpg.image_url)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Title: ${detail!!.title}", style = MaterialTheme.typography.bodyLarge, color = Color.White)
                    Text(text = "Synopsis: ${detail!!.synopsis}", style = MaterialTheme.typography.bodySmall, color = Color.White)
                    Row {
                        Text(text = "Genre: ", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                        LazyRow {
                            items(detail!!.genres) {
                                Text(text = it.name, style = MaterialTheme.typography.bodyMedium, color = Color.White)
                                Spacer(modifier = Modifier.width(3.dp))
                            }
                        }
                    }
                    Text(text = "Episodes: ${detail!!.episodes}", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                    Text(text = "Rating: ${detail!!.rating}", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun YouTubeScreen(videoUrl: String, posterUrl: String) {
    Column(modifier = Modifier.fillMaxWidth()
        .padding(16.dp)) {
        Spacer(modifier = Modifier.height(12.dp))
        YouTubePlayer(videoUrl = videoUrl, posterUrl = posterUrl, lifecycleOwner = LocalLifecycleOwner.current)
    }
}

@Composable
fun YouTubePlayer(videoUrl: String, posterUrl: String, lifecycleOwner: LifecycleOwner) {
    val context = LocalContext.current
    val url = "https://www.youtube.com/watch?v=$videoUrl?autoplay=1&vq=hd720"

    if(videoUrl == "null") {
        AsyncImage(
            modifier = Modifier.fillMaxWidth().height(500.dp).padding(5.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop,
            model = posterUrl,
            contentDescription = "Image from URL")
    } else {
        AndroidView(
            factory = {
                YouTubePlayerView(context = it).apply {
                    lifecycleOwner.lifecycle.addObserver(this)
                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(videoUrl, 0f)
                        }
                    })
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )
    }
}