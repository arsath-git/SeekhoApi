package com.example.seekhoapi.ui.view

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.seekhoapi.viewModel.PostViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.seekhoapi.DetailsActivity
import com.example.seekhoapi.data.models.Data
import com.example.seekhoapi.ui.theme.PurpleGrey40
import kotlin.jvm.java

@Composable
fun PostListScreen(viewModel: PostViewModel = PostViewModel(), paddingValues: PaddingValues) {
    val posts by viewModel.posts
    if(posts.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(posts) {
                PostCard(it)
            }
        }
    }
}

@Composable
fun PostCard(post: Data) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable{
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("api_key", post.mal_id)
                }
                context.startActivity(intent)
        },
        colors = CardDefaults.cardColors(PurpleGrey40)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize().padding(10.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
                model = post.images.jpg.image_url,
                contentDescription = "Image from URL")
            Text(text = "Title: ${post.title}", style = MaterialTheme.typography.bodyLarge, color = Color.White)
            Text(text = "Episodes: ${post.episodes}", style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Text(text = "Rating: ${post.score}", style = MaterialTheme.typography.bodySmall, color = Color.White)
        }
    }
}