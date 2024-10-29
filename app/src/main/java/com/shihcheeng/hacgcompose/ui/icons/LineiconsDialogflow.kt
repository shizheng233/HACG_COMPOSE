package com.shihcheeng.hacgcompose.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Iconify.LineiconsDialogflow: ImageVector
    get() {
        if (_LineiconsDialogflow != null) {
            return _LineiconsDialogflow!!
        }
        _LineiconsDialogflow = ImageVector.Builder(
            name = "LineiconsDialogflow 1",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(12f, 12.053f)
                lineTo(3.935f, 8.02f)
                verticalLineToRelative(6.815f)
                curveToRelative(-0.001f, 0.154f, 0.07f, 0.3f, 0.191f, 0.394f)
                lineTo(8.804f, 18f)
                arcToRelative(
                    0.32f,
                    0.32f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    0.172f,
                    0.293f
                )
                verticalLineToRelative(3.539f)
                arcToRelative(
                    0.172f,
                    0.172f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    0.262f,
                    0.141f
                )
                lineToRelative(10.596f, -6.745f)
                arcToRelative(
                    0.5f,
                    0.5f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    0.232f,
                    -0.424f
                )
                verticalLineTo(8.02f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000))) {
                moveToRelative(12f, 10.036f)
                lineToRelative(8.066f, -4.033f)
                lineToRelative(-7.945f, -3.972f)
                arcToRelative(
                    0.25f,
                    0.25f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.242f,
                    0f
                )
                lineTo(3.935f, 6.003f)
                close()
            }
        }.build()

        return _LineiconsDialogflow!!
    }

@Suppress("ObjectPropertyName")
private var _LineiconsDialogflow: ImageVector? = null
