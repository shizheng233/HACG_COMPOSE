/*
 * Feeder: Android RSS reader app
 * https://gitlab.com/spacecowboy/Feeder
 *
 * Copyright (C) 2022  Jonas Kalderstam
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.shihcheeng.hacgcompose.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle

/**
 * 适用于在[LazyListScope]中使用的[AnnotatedString.Builder]。我从
 * [ReadYou](https://github.com/Ashinch/ReadYou)中复制而来。
 * 简化了其实现方法。使用该方法可以在里面插入图片。
 *
 * @since 初始版本
 */
class TextComposer(
    val paragraphEmitter: (AnnotatedString.Builder) -> Unit,
) {

    val spanStack: MutableList<Span> = mutableListOf()

    /**
     * The identity of this will change - do not reference it in blocks
     */
    private var builder: AnnotatedString.Builder = AnnotatedString.Builder()

    /**
     * 重置该[builder]。
     */
    fun terminateCurrentText() {
        if (builder.toAnnotatedString().isEmpty()) {
            // Nothing to emit, and nothing to reset
            return
        }

        paragraphEmitter(builder)

        builder = AnnotatedString.Builder()

        for (span in spanStack) {
            when (span) {
                is SpanWithStyle -> builder.pushStyle(span.spanStyle)
                is SpanWithAnnotation -> builder.pushStringAnnotation(
                    tag = span.tag,
                    annotation = span.annotation
                )

            }
        }
    }


    /**
     * 添加两行。
     */
    fun ensureDoubleNewline() = apply {
        append("\n")
        append("\n")
    }

    /**
     * 添加一行。
     */
    fun ensureSingleNewline() = apply {
        append("\n")
    }

    /**
     * 添加文字。
     *
     * @param text 文本
     */
    fun append(text: String) = builder.append(text)

    fun append(char: Char) = builder.append(char)

    /**
     * 添加图片
     */
    fun <R> appendImage(
        link: String? = null,
        onLinkClick: (String) -> Unit,
        block: (
            onClick: (() -> Unit)?,
        ) -> R,
    ): R {
        val url = link ?: findClosestLink()
        // builder.ensureDoubleNewline()
        terminateCurrentText()
        val onClick: (() -> Unit)? = if (url?.isNotBlank() == true) {
            {
                onLinkClick(url)
            }
        } else {
            null
        }
        return block(onClick)
    }

    fun pop(index: Int) =
        builder.pop(index)

    fun pushStyle(style: SpanStyle): Int =
        builder.pushStyle(style)

    fun pushStringAnnotation(tag: String, annotation: String): Int =
        builder.pushStringAnnotation(tag = tag, annotation = annotation)


    private fun findClosestLink(): String? {
        for (span in spanStack.reversed()) {
            if (span is SpanWithAnnotation && span.tag == "URL") {
                return span.annotation
            }
        }
        return null
    }
}

inline fun <R : Any> TextComposer.withParagraph(
    crossinline block: TextComposer.() -> R,
): R {
    ensureSingleNewline()
    return block(this)
}

inline fun <R : Any> TextComposer.withChineseParagraph(
    crossinline block: TextComposer.() -> R,
): R {
    return block(this)
}

inline fun <R : Any> TextComposer.withStyle(
    style: SpanStyle,
    crossinline block: TextComposer.() -> R,
): R {
    spanStack.add(SpanWithStyle(style))
    val index = pushStyle(style)
    return try {
        block()
    } finally {
        pop(index)
        spanStack.remove(spanStack.last())
    }
}


inline fun <R : Any> TextComposer.withAnnotation(
    tag: String,
    annotation: String,
    crossinline block: TextComposer.() -> R,
): R {
    spanStack.add(SpanWithAnnotation(tag = tag, annotation = annotation))
    val index = pushStringAnnotation(tag = tag, annotation = annotation)
    return try {
        block()
    } finally {
        pop(index)
        spanStack.remove(spanStack.last())
    }
}

sealed class Span

data class SpanWithStyle(
    val spanStyle: SpanStyle,
) : Span()

data class SpanWithAnnotation(
    val tag: String,
    val annotation: String,
) : Span()

