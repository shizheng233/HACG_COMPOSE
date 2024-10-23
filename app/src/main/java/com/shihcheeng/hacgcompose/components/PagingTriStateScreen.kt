package com.shihcheeng.hacgcompose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState

@Composable
fun PagingTriStateScreen(
    modifier: Modifier = Modifier,
    loadState: LoadState,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    onError: @Composable (Throwable) -> Unit,
    onLoading: @Composable () -> Unit = { LoadingScreen() },
    onNotLoading: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .then(modifier)
    ) {
        when (loadState) {
            is LoadState.Error -> onError(loadState.error)
            LoadState.Loading -> onLoading()
            is LoadState.NotLoading -> onNotLoading()
        }
    }
}