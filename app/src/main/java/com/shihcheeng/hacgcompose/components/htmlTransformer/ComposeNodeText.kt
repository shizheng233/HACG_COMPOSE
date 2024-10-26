package com.shihcheeng.hacgcompose.components.htmlTransformer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shihcheeng.hacgcompose.ui.theme.ReaderBodyMedium
import com.shihcheeng.hacgcompose.ui.theme.ReaderShape
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode


fun LazyListScope.formatNodes(
    list: List<Node>
) {
    val composeNodeText = TextComposer { b ->
        item {
            val paragraph = b.toAnnotatedString()
            Text(
                text = paragraph,
                style = ReaderBodyMedium,
                textAlign = TextAlign.Start
            )
        }
    }

    composeNodeText.tagsFormater(lazyListScope = this, list = list)
    composeNodeText.terminateCurrentText()
}


private fun TextComposer.tagsFormater(
    lazyListScope: LazyListScope,
    list: List<Node>,
    preString: Boolean = false,
) {
    var node = list.firstOrNull()

    while (node != null) {
        when (node) {
            is TextNode -> {
                append(node.text().trim())
            }

            is Element -> {
                val element = node
                when (element.tagName()) {
                    "p" -> {
                        withParagraph {
                            tagsFormater(lazyListScope, element.childNodes())
                        }
                    }

                    "img" -> {
                        val image = element.attr("src")
                        val imageDescription = image.split("/").last()
                        appendImage(
                            link = image,
                            onLinkClick = {

                            }
                        ) {
                            lazyListScope.item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                ) {
                                    AsyncImage(
                                        model = image,
                                        contentScale = ContentScale.Crop,
                                        contentDescription = imageDescription,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .clip(ReaderShape)
                                            .clickable(onClick = { it?.invoke() })
                                    )
                                    Text(
                                        text = imageDescription,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(vertical = 4.dp),
                                        style = MaterialTheme.typography.labelMedium,
                                        fontStyle = FontStyle.Italic
                                    )
                                }
                            }
                        }
                    }

                    "div" -> {
                        tagsFormater(lazyListScope, element.childNodes())
                    }

                    "del", "s" -> {
                        withStyle(
                            ReaderBodyMedium.copy(
                                textDecoration = TextDecoration.LineThrough
                            ).toSpanStyle()
                        ) {
                            tagsFormater(
                                lazyListScope,
                                element.childNodes()
                            )
                        }
                    }

                    "br" -> {
                        appendCompose {
                            lazyListScope.item {
                                Spacer(Modifier.height(4.dp))
                            }
                        }
                    }

                    "span" -> {
                        //tagsFormater(lazyListScope, element.childNodes())
                    }

                    "strong" -> {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            tagsFormater(lazyListScope, element.childNodes())
                        }
                    }

                    "ul" -> {
                        appendCompose {
                            lazyListScope.item {
                                ULTagPreview(element = element)
                            }
                        }
                    }


                    "h2" -> {
                        appendCompose {
                            lazyListScope.item {
                                Text(
                                    text = element.text(),
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }
                        }
                    }

                    "pre" -> {
                        appendCompose {
                            lazyListScope.item {
                                PreTagView(
                                    element = element,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                    }

                    "hr" -> {
                        appendCompose {
                            lazyListScope.item {
                                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                            }
                        }
                    }

                    "audio" -> {
                        appendCompose {
                            lazyListScope.item {
                                AudioTagPreview(
                                    audioLink = element.attr("src")
                                )
                            }
                        }
                    }

                    "table" -> {
                        appendTable {
                            tableTagPreview(
                                lazyListScope = lazyListScope,
                                element = element,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }

                    else -> {
                        tagsFormater(lazyListScope, element.childNodes())
                    }
                }
            }
        }
        node = node.nextSibling()
    }
}



