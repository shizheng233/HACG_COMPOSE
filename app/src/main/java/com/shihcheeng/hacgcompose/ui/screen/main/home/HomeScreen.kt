package com.shihcheeng.hacgcompose.ui.screen.main.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.shihcheeng.hacgcompose.components.ErrorScreen
import com.shihcheeng.hacgcompose.components.PagingTriStateScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onNav: (String) -> Unit
) {
    val data = viewModel.data.collectAsLazyPagingItems()
    PagingTriStateScreen(
        loadState = data.loadState.refresh,
        onError = {
            ErrorScreen(
                errorMessage = it.localizedMessage
            ) {
                data.refresh()
            }
        }
    ) {
        HomeScreenContent(list = data, modifier = modifier) {
            onNav(it.split("/").last())
        }
    }
}