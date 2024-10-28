package com.shihcheeng.hacgcompose.ui.screen.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.shihcheeng.hacgcompose.components.ErrorItem
import com.shihcheeng.hacgcompose.components.MainCardView
import com.shihcheeng.hacgcompose.components.pagingTriStateScreen
import com.shihcheeng.hacgcompose.datamodel.MainDataModel

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<MainDataModel>,
    onClick: (id: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(list.itemCount) { index ->
            list[index]?.let {
                MainCardView(mainModel = it) {
                    onClick(it.href)
                }
            }
        }
        pagingTriStateScreen(
            loadState = list.loadState.append,
            onError = {
                ErrorItem(errorMessage = it.localizedMessage, onRetry = list::retry)
            }
        )
    }
}

