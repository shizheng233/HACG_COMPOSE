package com.shihcheeng.hacgcompose.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Iconify.MdiMagnet: ImageVector
    get() {
        if (_MdiMagnet != null) {
            return _MdiMagnet!!
        }
        _MdiMagnet = ImageVector.Builder(
            name = "MdiMagnet",
            defaultWidth = 1.dp,
            defaultHeight = 1.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(3f, 7f)
                verticalLineToRelative(6f)
                arcToRelative(9f, 9f, 0f, isMoreThanHalf = false, isPositiveArc = false, 9f, 9f)
                arcToRelative(9f, 9f, 0f, isMoreThanHalf = false, isPositiveArc = false, 9f, -9f)
                verticalLineTo(7f)
                horizontalLineToRelative(-4f)
                verticalLineToRelative(6f)
                arcToRelative(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -5f, 5f)
                arcToRelative(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -5f, -5f)
                verticalLineTo(7f)
                moveToRelative(10f, -2f)
                horizontalLineToRelative(4f)
                verticalLineTo(2f)
                horizontalLineToRelative(-4f)
                moveTo(3f, 5f)
                horizontalLineToRelative(4f)
                verticalLineTo(2f)
                horizontalLineTo(3f)
            }
        }.build()

        return _MdiMagnet!!
    }

@Suppress("ObjectPropertyName")
private var _MdiMagnet: ImageVector? = null
