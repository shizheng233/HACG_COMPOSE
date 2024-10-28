package com.shihcheeng.hacgcompose.ui.screen.main.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.shihcheeng.hacgcompose.components.ErrorScreen
import com.shihcheeng.hacgcompose.components.MainCardView
import com.shihcheeng.hacgcompose.components.PagingTriStateScreen

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    onNav: (String) -> Unit
) {

    val data = viewModel.data.collectAsLazyPagingItems()

    PagingTriStateScreen(
        loadState = data.loadState.refresh,
        onError = {
            ErrorScreen(errorMessage = it.localizedMessage) {
                data.refresh()
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(data.itemCount) { index ->
                data[index]?.let { model ->
                    MainCardView(mainModel = model) {
                        model.href.split("/").last().let(onNav)
                    }
                }
            }
        }
    }

}