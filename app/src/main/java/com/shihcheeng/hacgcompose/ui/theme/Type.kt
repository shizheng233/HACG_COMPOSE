package com.shihcheeng.hacgcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)


val ReaderBodyMedium = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
    lineHeight = (18 * 1.5).sp,
    letterSpacing = 0.1.sp,
    fontStyle = FontStyle.Normal,
)

val ReaderBodySmall = SpanStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    letterSpacing = 0.1.sp,
    fontStyle = FontStyle.Normal,
)

val ReaderTitleMedium = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    lineHeight = (28 * 1.5).sp,
    letterSpacing = 0.2.sp,
    fontStyle = FontStyle.Normal
)

val TextStyle.ToCharIndent: TextIndent
    get() {
        return TextIndent(firstLine = fontSize.times(2))
    }
