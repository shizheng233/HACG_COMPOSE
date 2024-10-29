package com.shihcheeng.hacgcompose.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


val Iconify.MaterialSymbolsHostOutline: ImageVector
    get() {
        if (_MaterialSymbolsHostOutline != null) {
            return _MaterialSymbolsHostOutline!!
        }
        _MaterialSymbolsHostOutline = ImageVector.Builder(
            name = "MaterialSymbolsHostOutline",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(4f, 21f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                reflectiveQuadTo(2f, 19f)
                lineTo(2f, 5f)
                quadToRelative(0f, -0.825f, 0.588f, -1.412f)
                reflectiveQuadTo(4f, 3f)
                horizontalLineToRelative(5f)
                quadToRelative(0.825f, 0f, 1.413f, 0.588f)
                reflectiveQuadTo(11f, 5f)
                verticalLineToRelative(14f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                reflectiveQuadTo(9f, 21f)
                close()
                moveTo(15f, 21f)
                quadToRelative(-0.825f, 0f, -1.412f, -0.587f)
                reflectiveQuadTo(13f, 19f)
                lineTo(13f, 5f)
                quadToRelative(0f, -0.825f, 0.588f, -1.412f)
                reflectiveQuadTo(15f, 3f)
                horizontalLineToRelative(5f)
                quadToRelative(0.825f, 0f, 1.413f, 0.588f)
                reflectiveQuadTo(22f, 5f)
                verticalLineToRelative(14f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                reflectiveQuadTo(20f, 21f)
                close()
                moveTo(4f, 19f)
                horizontalLineToRelative(5f)
                lineTo(9f, 5f)
                lineTo(4f, 5f)
                close()
                moveTo(15f, 19f)
                horizontalLineToRelative(5f)
                lineTo(20f, 5f)
                horizontalLineToRelative(-5f)
                close()
                moveTo(5f, 15f)
                horizontalLineToRelative(3f)
                verticalLineToRelative(-2f)
                lineTo(5f, 13f)
                close()
                moveTo(16f, 15f)
                horizontalLineToRelative(3f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(-3f)
                close()
                moveTo(5f, 12f)
                horizontalLineToRelative(3f)
                verticalLineToRelative(-2f)
                lineTo(5f, 10f)
                close()
                moveTo(16f, 12f)
                horizontalLineToRelative(3f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(-3f)
                close()
                moveTo(5f, 9f)
                horizontalLineToRelative(3f)
                lineTo(8f, 7f)
                lineTo(5f, 7f)
                close()
                moveTo(16f, 9f)
                horizontalLineToRelative(3f)
                lineTo(19f, 7f)
                horizontalLineToRelative(-3f)
                close()
                moveTo(4f, 19f)
                horizontalLineToRelative(5f)
                close()
                moveTo(15f, 19f)
                horizontalLineToRelative(5f)
                close()
            }
        }.build()

        return _MaterialSymbolsHostOutline!!
    }

@Suppress("ObjectPropertyName")
private var _MaterialSymbolsHostOutline: ImageVector? = null
