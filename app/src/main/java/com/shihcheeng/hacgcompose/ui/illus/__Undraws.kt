package com.shihcheeng.hacgcompose.ui.illus

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.shihcheeng.hacgcompose.ui.illus.undraws.UndrawLocationSearchReTtoj
import kotlin.collections.List as ____KtList

object Undraws

private var Search_Re: ____KtList<ImageVector>? = null

val Undraws.SearchRe: ____KtList<ImageVector>
    @Composable
    get() {
        if (Search_Re != null) {
            return Search_Re!!
        }
        Search_Re = listOf(UndrawLocationSearchReTtoj)
        return Search_Re!!
    }
