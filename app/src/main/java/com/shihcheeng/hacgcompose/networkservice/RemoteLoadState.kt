package com.shihcheeng.hacgcompose.networkservice

import androidx.compose.runtime.Immutable

sealed class RemoteLoadState<out T> {

    @Immutable
    data class Success<T>(val data: T) : RemoteLoadState<T>()

    @Immutable
    data class Error(val error: Throwable) : RemoteLoadState<Nothing>()

    @Immutable
    data object Loading : RemoteLoadState<Nothing>()

}