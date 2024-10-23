package com.shihcheeng.hacgcompose.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle

class HacgAnnotatedStringBuilder {

    private val builder: AnnotatedString.Builder = AnnotatedString.Builder()

    val lastTwoChars: MutableList<Char> = mutableListOf()

    val length: Int
        get() = builder.length

    val endsWithWhitespace: Boolean
        get() {
            if (lastTwoChars.isEmpty()) {
                return true
            }
            lastTwoChars.peekLatest()?.let { latest ->
                // Non-breaking space (160) is not caught by trim or whitespace identification
                if (latest.isWhitespace() || latest.code == 160) {
                    return true
                }
            }

            return false
        }

    fun pushStyle(style: SpanStyle): Int =
        builder.pushStyle(style = style)

    fun pop(index: Int) =
        builder.pop(index)

    fun pushStringAnnotation(tag: String, annotation: String): Int =
        builder.pushStringAnnotation(tag = tag, annotation = annotation)

    fun append(text: String) {
        if (text.count() >= 2) {
            lastTwoChars.pushMaxTwo(text.secondToLast())
        }
        if (text.isNotEmpty()) {
            lastTwoChars.pushMaxTwo(text.last())
        }
        builder.append(text)
    }

    fun append(char: Char) {
        lastTwoChars.pushMaxTwo(char)
        builder.append(char)
    }


    fun toAnnotatedString(): AnnotatedString {
        return builder.toAnnotatedString()
    }


}

fun HacgAnnotatedStringBuilder.isEmpty() = lastTwoChars.isEmpty()
fun HacgAnnotatedStringBuilder.isNotEmpty() = lastTwoChars.isNotEmpty()

fun HacgAnnotatedStringBuilder.ensureDoubleNewline() {
    when {
        lastTwoChars.isEmpty() -> {
            // Nothing to do
        }

        length == 1 && lastTwoChars.peekLatest()?.isWhitespace() == true -> {
            // Nothing to do
        }

        length == 2 &&
                lastTwoChars.peekLatest()?.isWhitespace() == true &&
                lastTwoChars.peekSecondLatest()?.isWhitespace() == true -> {
            // Nothing to do
        }

        lastTwoChars.peekLatest() == '\n' && lastTwoChars.peekSecondLatest() == '\n' -> {
            // Nothing to do
        }

        lastTwoChars.peekLatest() == '\n' -> {
            append('\n')
        }

        else -> {
            append("\n\n")
        }
    }
}

private fun HacgAnnotatedStringBuilder.ensureSingleNewline() {
    when {
        lastTwoChars.isEmpty() -> {
            // Nothing to do
        }

        length == 1 && lastTwoChars.peekLatest()?.isWhitespace() == true -> {
            // Nothing to do
        }

        lastTwoChars.peekLatest() == '\n' -> {
            // Nothing to do
        }

        else -> {
            append('\n')
        }
    }
}

private fun <T> MutableList<T>.pushMaxTwo(item: T) {
    this.add(0, item)
    if (count() > 2) {
        this.remove(last())
    }
}

private fun CharSequence.secondToLast(): Char {
    if (count() < 2) {
        throw NoSuchElementException("List has less than two items.")
    }
    return this[lastIndex - 1]
}

private fun <T> List<T>.peekLatest(): T? {
    return this.firstOrNull()
}

private fun <T> List<T>.peekSecondLatest(): T? {
    if (count() < 2) {
        return null
    }
    return this[1]
}