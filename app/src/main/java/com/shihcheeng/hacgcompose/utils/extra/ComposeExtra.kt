package com.shihcheeng.hacgcompose.utils.extra

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

fun PaddingValues.margeWith(
    layoutDirection: LayoutDirection,
    top: Dp = 0.dp,
    bottom: Dp = 0.dp,
    start: Dp = 0.dp,
    end: Dp = 0.dp
) = PaddingValues(
    top = this.calculateTopPadding() + top,
    bottom = this.calculateBottomPadding() + bottom,
    start = this.calculateStartPadding(layoutDirection) + start,
    end = this.calculateEndPadding(layoutDirection) + end
)

fun Context.toast(text: String, duration: Int) {
    Toast.makeText(this, text, duration).show()
}