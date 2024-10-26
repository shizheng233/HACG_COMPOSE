package com.shihcheeng.hacgcompose.components.htmlTransformer

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode


fun tableTagPreview(
    lazyListScope: LazyListScope,
    modifier: Modifier = Modifier,
    element: Element
) {
    lazyListScope.items(
        element.getElementsByTag("tr")
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .then(modifier),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        state = rememberScrollState()
                    )
                    .padding(all = 8.dp)
            ) {
                AsyncImage(
                    model = it.getElementsByTag("img").attr("src"),
                    contentDescription = null,
                    modifier = Modifier
                        .width(150.dp)
                        .aspectRatio(3f / 4f)
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.medium),
                    placeholder = ColorPainter(MaterialTheme.colorScheme.secondaryContainer),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            tableReformat(this, it.childNodes())
                        }
                    )
                }
            }
        }
    }
}

private fun tableReformat(
    annotation: AnnotatedString.Builder,
    node: List<Node>
) {
    var nodeInner = node.firstOrNull()
    while (nodeInner != null) {
        when (nodeInner) {
            is TextNode -> {
                annotation.append(nodeInner.text())
            }

            is Element -> {
                when (nodeInner.tagName()) {
                    "br" -> annotation.append("\n")
                    else -> tableReformat(annotation, nodeInner.childNodes())
                }
            }
        }
        nodeInner = nodeInner.nextSibling()
    }
}


