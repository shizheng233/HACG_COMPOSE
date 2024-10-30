package com.shihcheeng.hacgcompose.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Iconify.CiDoubleQuotesR: ImageVector
    get() {
        if (_CiDoubleQuotesR != null) {
            return _CiDoubleQuotesR!!
        }
        _CiDoubleQuotesR = ImageVector.Builder(
            name = "CiDoubleQuotesR",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16f, 17f)
                arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 3f, -3f)
                verticalLineToRelative(-2f)
                moveToRelative(0f, 0f)
                verticalLineTo(8.598f)
                curveToRelative(0f, -0.558f, 0f, -0.838f, -0.109f, -1.052f)
                arcToRelative(
                    1f,
                    1f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.437f,
                    -0.437f
                )
                curveTo(18.24f, 7f, 17.96f, 7f, 17.4f, 7f)
                horizontalLineToRelative(-1.8f)
                curveToRelative(-0.56f, 0f, -0.84f, 0f, -1.054f, 0.109f)
                arcToRelative(
                    1f,
                    1f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.437f,
                    0.437f
                )
                curveTo(14f, 7.76f, 14f, 8.04f, 14f, 8.6f)
                verticalLineToRelative(1.8f)
                curveToRelative(0f, 0.56f, 0f, 0.84f, 0.109f, 1.054f)
                arcToRelative(
                    1f,
                    1f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    0.437f,
                    0.437f
                )
                curveToRelative(0.214f, 0.109f, 0.494f, 0.109f, 1.053f, 0.109f)
                close()
                moveTo(7f, 17f)
                arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 3f, -3f)
                verticalLineToRelative(-2f)
                moveToRelative(0f, 0f)
                verticalLineTo(8.598f)
                curveToRelative(0f, -0.559f, 0f, -0.838f, -0.109f, -1.052f)
                arcToRelative(
                    1f,
                    1f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.437f,
                    -0.437f
                )
                curveTo(9.24f, 7f, 8.96f, 7f, 8.4f, 7f)
                horizontalLineTo(6.6f)
                curveToRelative(-0.56f, 0f, -0.84f, 0f, -1.054f, 0.109f)
                arcToRelative(
                    1f,
                    1f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    -0.437f,
                    0.437f
                )
                curveTo(5f, 7.76f, 5f, 8.04f, 5f, 8.6f)
                verticalLineToRelative(1.8f)
                curveToRelative(0f, 0.56f, 0f, 0.84f, 0.109f, 1.054f)
                arcToRelative(
                    1f,
                    1f,
                    0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    0.437f,
                    0.437f
                )
                curveTo(5.76f, 12f, 6.04f, 12f, 6.598f, 12f)
                close()
            }
        }.build()

        return _CiDoubleQuotesR!!
    }

@Suppress("ObjectPropertyName")
private var _CiDoubleQuotesR: ImageVector? = null
