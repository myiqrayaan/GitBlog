package com.example.gitblog2.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.gitblog2.R
import com.example.gitblog2.domain.models.BlogModel

@Composable
fun BlogCard(blog: BlogModel, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        BlogCardImage(blog.thumbnailUrl)
        Text(text = blog.title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(10.dp))
    }
}

@Composable
private fun BlogCardImage(imageUrl: String) {
    val imageRequest =
        ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(enable = true).build()
    AsyncImage(
        model = imageRequest,
        contentScale = ContentScale.Crop,
        contentDescription = "blog card image",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ratio = 2f),
        placeholder = painterResource(R.drawable.news),
        error = painterResource(R.drawable.news)
    )
}