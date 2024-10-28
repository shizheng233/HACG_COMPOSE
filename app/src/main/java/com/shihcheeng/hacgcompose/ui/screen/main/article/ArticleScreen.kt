package com.shihcheeng.hacgcompose.ui.screen.main.article

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
fun ArticleScreen(
    viewModel: ArticleViewModel = hiltViewModel(),
    onNav: (String) -> Unit
) {
    val data = viewModel.data.collectAsLazyPagingItems()
    PagingTriStateScreen(
        loadState = data.loadState.refresh,
        onError = {
            ErrorScreen(errorMessage = it.message, onRetry = data::retry)
        },
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(data.itemCount) { index ->
                data[index]?.let {
                    MainCardView(mainModel = it) {
                        it.href.split("/").last().let(onNav)
                    }
                }
            }
            pagingTriStateScreen(
                loadState = data.loadState.append,
                onError = {
                    ErrorItem(errorMessage = it.localizedMessage, onRetry = data::refresh)
                }
            )
        }
    }
}