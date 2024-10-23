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

/**
 * Tir-State in-list item.With this item, you can insert [onSuccess] layout for
 * [RemoteLoadState.Loading], [onLoad] layout for [RemoteLoadState.Loading] and
 * [onError] layout for [RemoteLoadState.Error].The success layout will provide
 * a [T].
 *
 * @param remoteLoadState Provide a [RemoteLoadState] for observe.
 * @param onLoad loading layout for [RemoteLoadState.Loading].
 * @param onError error layout for [RemoteLoadState.Error].
 * @param onSuccess success layout for [RemoteLoadState.Success].
 */
inline fun <reified T> LazyListScope.triState(
    remoteLoadState: RemoteLoadState<T>,
    crossinline onLoad: @Composable () -> Unit = { LoadingItem() },
    crossinline onError: @Composable (Throwable) -> Unit,
    onSuccess: LazyListScope.(data: T) -> Unit
) {
    when (remoteLoadState) {
        is RemoteLoadState.Error -> {
            item {
                onError(remoteLoadState.error)
            }
        }

        RemoteLoadState.Loading -> item {
            onLoad()
        }

        is RemoteLoadState.Success -> onSuccess(remoteLoadState.data)
    }
}