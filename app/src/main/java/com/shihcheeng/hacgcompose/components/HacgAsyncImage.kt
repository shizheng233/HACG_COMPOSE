package com.shihcheeng.hacgcompose.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun HacgAsyncImage(
    modifier: Modifier = Modifier,
    contentDestination: String? = null,
    url: String,
) {
    AsyncImage(
        model = url,
        modifier = Modifier.then(modifier),
        contentDescription = contentDestination,
        contentScale = ContentScale.Crop,
        placeholder = ColorPainter(MaterialTheme.colorScheme.secondaryContainer),
        error = ColorPainter(MaterialTheme.colorScheme.errorContainer)
    )
}