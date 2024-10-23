package com.shihcheeng.hacgcompose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shihcheeng.hacgcompose.networkservice.RemoteLoadState

@Composable
fun <T> TriStateScreen(
    modifier: Modifier = Modifier,
    remoteLoadState: RemoteLoadState<T>,
    onLoad: @Composable () -> Unit = { LoadingScreen() },
    onError: @Composable (Throwable) -> Unit,
    onSuccess: @Composable (data: T) -> Unit
) {
    Box(modifier = modifier) {
        when (remoteLoadState) {
            is RemoteLoadState.Error -> onError(remoteLoadState.error)
            RemoteLoadState.Loading -> onLoad()
            is RemoteLoadState.Success -> onSuccess(remoteLoadState.data)
        }
    }
}

fun <T> LazyListScope.triState(
    remoteLoadState: RemoteLoadState<T>,
    onLoad: @Composable () -> Unit = { LoadingItem() },
    onError: @Composable (Throwable) -> Unit,
    onSuccess: LazyListScope.(data: T) -> Unit
) {
    when (remoteLoadState) {
        is RemoteLoadState.Error -> {
            item(key = TriStateKey.ERROR) {
                onError(remoteLoadState.error)
            }
        }

        RemoteLoadState.Loading -> item(key = TriStateKey.LOAD) {
            onLoad()
        }

        is RemoteLoadState.Success -> onSuccess(remoteLoadState.data)
    }
}

enum class TriStateKey {
    LOAD, ERROR
}