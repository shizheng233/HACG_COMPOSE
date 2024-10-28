package com.shihcheeng.hacgcompose.ui.screen.main.constants

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Composable
fun topAppBarColorDetected(
    scrollBehavior: TopAppBarScrollBehavior
): Color {
    val colorTransitionFraction by remember(scrollBehavior) {
        derivedStateOf {
            val overlappingFraction = scrollBehavior.state.overlappedFraction
            if (overlappingFraction > 0.01f) 1f else 0f
        }
    }
    val appBarContainerColor by animateColorAsState(
        targetValue = containerColor(colorTransitionFraction),
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "third_color_of_bar"
    )
    return appBarContainerColor
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Composable
private fun containerColor(
    colorTransitionFraction: Float
): Color {
    return lerp(
        TopAppBarDefaults.topAppBarColors().containerColor,
        TopAppBarDefaults.topAppBarColors().scrolledContainerColor,
        FastOutLinearInEasing.transform(colorTransitionFraction)
    )
}