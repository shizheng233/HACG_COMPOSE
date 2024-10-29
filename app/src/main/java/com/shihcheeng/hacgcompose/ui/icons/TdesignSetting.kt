package com.shihcheeng.hacgcompose.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Iconify.TDesignSetting: ImageVector
    get() {
        if (_TdesignSetting != null) {
            return _TdesignSetting!!
        }
        _TdesignSetting = ImageVector.Builder(
            name = "TdesignSetting",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveToRelative(12f, 0.845f)
                lineToRelative(9.66f, 5.578f)
                verticalLineToRelative(11.154f)
                lineTo(12f, 23.155f)
                lineToRelative(-9.66f, -5.578f)
                lineTo(2.34f, 6.423f)
                close()
                moveTo(12f, 3.155f)
                lineTo(4.34f, 7.577f)
                verticalLineToRelative(8.846f)
                lineTo(12f, 20.845f)
                lineToRelative(7.66f, -4.422f)
                lineTo(19.66f, 7.577f)
                close()
                moveTo(12f, 9f)
                arcToRelative(3f, 3f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, 6f)
                arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, -6f)
                moveToRelative(-5f, 3f)
                arcToRelative(5f, 5f, 0f, isMoreThanHalf = true, isPositiveArc = true, 10f, 0f)
                arcToRelative(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -10f, 0f)
            }
        }.build()

        return _TdesignSetting!!
    }

@Suppress("ObjectPropertyName")
private var _TdesignSetting: ImageVector? = null
