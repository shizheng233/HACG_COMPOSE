package com.shihcheeng.hacgcompose.components.htmlTransformer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jsoup.nodes.Element

@Composable
fun ULTagPreview(
    modifier: Modifier = Modifier,
    element: Element
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .then(modifier),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            element.getElementsByTag("li").forEach {
                Text(
                    text = "\u2022 ${it.text()}",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }
        }
    }
}