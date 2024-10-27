package com.shihcheeng.hacgcompose.ui.screen.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shihcheeng.hacgcompose.R
import com.shihcheeng.hacgcompose.components.ErrorItem
import com.shihcheeng.hacgcompose.components.ErrorScreen
import com.shihcheeng.hacgcompose.components.TriStateScreen
import com.shihcheeng.hacgcompose.components.htmlTransformer.formatNodes
import com.shihcheeng.hacgcompose.components.triState
import com.shihcheeng.hacgcompose.utils.extra.margeWith

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    onBackUp: () -> Unit
) {
    val title by detailViewModel.title.collectAsStateWithLifecycle()
    val data by detailViewModel.nodes.collectAsStateWithLifecycle()
    val titlePlain by detailViewModel.titlePlain.collectAsStateWithLifecycle()
    val comments by detailViewModel.comments.collectAsStateWithLifecycle()
    val lazyState = rememberLazyListState()
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val firstIndex by remember { derivedStateOf { lazyState.firstVisibleItemIndex } }
                    AnimatedVisibility(
                        visible = firstIndex != 0,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text(text = titlePlain, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackUp
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = stringResource(R.string.back_to_up)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        TriStateScreen(
            remoteLoadState = title,
            onError = { e ->
                ErrorScreen(errorMessage = e.localizedMessage) {
                    detailViewModel.load()
                }
            },
            modifier = Modifier.padding(
                top = it.calculateTopPadding()
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                contentPadding = PaddingValues(horizontal = 16.dp)
                    .margeWith(
                        layoutDirection = LocalLayoutDirection.current,
                        bottom = WindowInsets.systemBars
                            .asPaddingValues(LocalDensity.current)
                            .calculateBottomPadding()
                    ),
                state = lazyState
            ) {
                item(key = DetailKey.TOP) {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                item(key = DetailKey.TIME_AND_AUTHOR) {
                    Text(
                        text = stringResource(R.string.detail_info_author, it.time, it.author),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                triState(
                    remoteLoadState = data,
                    onError = { e ->
                        ErrorItem(errorMessage = e.localizedMessage) {
                            detailViewModel.load()
                        }
                    }
                ) { data ->
                    formatNodes(data)
                }
                triState(
                    remoteLoadState = comments,
                    onError = { e ->
                        ErrorItem(errorMessage = e.localizedMessage) {
                            detailViewModel.load()
                        }
                    },
                    onSuccess = { datas ->
                        if (datas.isNotEmpty()) {
                            item(
                                key = DetailKey.COMMENTS
                            ) {
                                Text(
                                    text = stringResource(R.string.comments),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        items(
                            items = datas
                        ) { x ->
                            DetailComment(
                                contentComment = x
                            )
                        }
                    }
                )
            }
        }
    }

}