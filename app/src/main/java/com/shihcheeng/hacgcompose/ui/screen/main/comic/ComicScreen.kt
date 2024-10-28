package com.shihcheeng.hacgcompose.ui.screen.main.comic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.shihcheeng.hacgcompose.components.ErrorItem
import com.shihcheeng.hacgcompose.components.ErrorScreen
import com.shihcheeng.hacgcompose.components.MainCardView
import com.shihcheeng.hacgcompose.components.PagingTriStateScreen
import com.shihcheeng.hacgcompose.components.pagingTriStateScreen

@Composable
fun ComicScreen(
    viewModel: ComicViewModel = hiltViewModel(),
    onNav: (String) -> Unit
) {

    val data = viewModel.data.collectAsLazyPagingItems()

    PagingTriStateScreen(
        loadState = data.loadState.refresh,
        onError = {
            ErrorScreen(
                errorMessage = it.localizedMessage,
                onRetry = data::refresh
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(data.itemCount) { index ->
                data[index]?.let {
                    MainCardView(mainModel = it) {
                        it.href.split("/").last().let(onNav)
                    }
                }
            }
            pagingTriStateScreen(
                loadState = data.loadState.prepend,
                onError = {
                    ErrorItem(errorMessage = it.localizedMessage, onRetry = data::retry)
                }
            )
        }
    }

}