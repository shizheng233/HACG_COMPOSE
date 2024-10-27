package com.shihcheeng.hacgcompose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState

/**
 * 适合于[LoadState]的三状态内容。
 *
 * @param onLoading 当[LoadState.Loading]触发时显示。
 * @param onError 当[LoadState.Error]触发时显示。
 * @param onNotLoading 当[LoadState.NotLoading]触发时显示。
 */
@Composable
fun PagingTriStateScreen(
    modifier: Modifier = Modifier,
    loadState: LoadState,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    onError: @Composable (Throwable) -> Unit,
    onLoading: @Composable (thisState: LoadState) -> Unit = { LoadingScreen() },
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
            LoadState.Loading -> onLoading(loadState)
            is LoadState.NotLoading -> onNotLoading()
        }
    }
}