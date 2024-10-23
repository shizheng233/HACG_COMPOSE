package com.shihcheeng.hacgcompose.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import soup.compose.material.motion.animation.materialFadeThroughIn
import soup.compose.material.motion.animation.materialFadeThroughOut

@Composable
fun AnimatedPager(
    modifier: Modifier = Modifier,
    saveableStateHolder: SaveableStateHolder = rememberSaveableStateHolder(),
    contentsPadding: PaddingValues = PaddingValues(0.dp),
    selected: Int,
    contents: @Composable AnimatedContentScope.(Int) -> Unit
) {
    AnimatedContent(
        targetState = selected,
        label = "animated_pager",
        modifier = Modifier
            .padding(contentsPadding)
            .then(modifier),
        transitionSpec = { materialFadeThroughIn() togetherWith materialFadeThroughOut() },
        contentAlignment = Alignment.Center
    ) { s ->
        saveableStateHolder.SaveableStateProvider("PAGE_${s}") {
            contents(this, s)
        }
    }
}