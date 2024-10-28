package com.shihcheeng.hacgcompose.ui.screen.search

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.shihcheeng.hacgcompose.R
import com.shihcheeng.hacgcompose.components.ErrorScreen
import com.shihcheeng.hacgcompose.components.PagingTriStateScreen
import com.shihcheeng.hacgcompose.components.WaitingSearchScreen
import com.shihcheeng.hacgcompose.utils.extra.margeWith

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onItemClick: (String) -> Unit
) {

    val word by viewModel.searchWord.collectAsStateWithLifecycle()
    val (value, onValueChange) = rememberSaveable { mutableStateOf("") }
    val data = viewModel.emitSearch.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = {
                        BasicTextField(
                            value = value,
                            onValueChange = onValueChange,
                            singleLine = true,
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            decorationBox = {
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = value.isEmpty(),
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    Text(
                                        text = stringResource(R.string.input_search_text),
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                it()
                            },
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    viewModel.search(value)
                                }
                            ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = onBack
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_arrow_back_24),
                                contentDescription = stringResource(R.string.back_to_up)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors()
                        .copy(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            titleContentColor = contentColorFor(MaterialTheme.colorScheme.surfaceContainerHigh)
                        ),
                    actions = {
                        if (value.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    onValueChange("")
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_clear_24),
                                    contentDescription = stringResource(R.string.clear_text)
                                )
                            }
                        }
                    }
                )
                HorizontalDivider()
            }
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh, //依据官方网站。
        contentColor = contentColorFor(MaterialTheme.colorScheme.surfaceContainerHigh) //无法判断，直接使用谷歌给的方法。
    ) { padding ->
        if (word.isEmpty()) {
            WaitingSearchScreen()
        } else {
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
                LazyColumn(
                    contentPadding = padding.margeWith(
                        layoutDirection = LocalLayoutDirection.current,
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(data.itemCount) { index ->
                        data[index]?.let {
                            SearchViewItem(
                                mainModel = it
                            ) {
                                it.href.split("/").last().let(onItemClick)
                            }
                        }
                    }
                }
            }
        }
    }
}


