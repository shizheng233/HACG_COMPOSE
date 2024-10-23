package com.shihcheeng.hacgcompose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import coil.compose.AsyncImage
import com.shihcheeng.hacgcompose.ui.theme.ReaderBodyMedium
import com.shihcheeng.hacgcompose.ui.theme.ReaderShape
import com.shihcheeng.hacgcompose.ui.theme.ReaderTitleMedium
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode


fun LazyListScope.formatNodes(list: List<Node>) {
    val composeNodeText = TextComposer { b ->
        item {
            val paragraph = b.toAnnotatedString()
            Text(
                text = paragraph,
                style = ReaderBodyMedium
            )
        }
    }

    composeNodeText.tagsFormater(lazyListScope = this, list)
    composeNodeText.terminateCurrentText()
}


private fun TextComposer.tagsFormater(lazyListScope: LazyListScope, list: List<Node>) {
    var node = list.firstOrNull()
    while (node != null) {
        when (node) {
            is TextNode -> {
                append(node.wholeText)
            }

            is Element -> {
                val element = node
                when (element.tagName()) {
                    "p" -> {
                        withChineseParagraph {
                            tagsFormater(lazyListScope, element.childNodes())
                        }
                    }

                    "img" -> {
                        val image = element.attr("src")
                        appendImage(
                            link = image,
                            onLinkClick = { }
                        ) {
                            lazyListScope.item {
                                AsyncImage(
                                    model = image,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .clip(ReaderShape)
                                )
                            }
                        }
                    }

                    "div" -> {
                        tagsFormater(lazyListScope, element.childNodes())
                    }

                    "del" -> {
                        withStyle(
                            ReaderBodyMedium.copy(
                                textDecoration = TextDecoration.LineThrough
                            ).toSpanStyle()
                        ) {
                            append((element.text()))
                        }
                    }

                    "br" -> {
                        ensureSingleNewline()
                    }

                    "span" -> {
                        tagsFormater(lazyListScope, element.childNodes())
                    }

                    "h2" -> {
                        withStyle(ReaderTitleMedium.toSpanStyle()) {
                            append(element.text())
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

