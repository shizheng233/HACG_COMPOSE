package com.shihcheeng.hacgcompose.ui.screen.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.shihcheeng.hacgcompose.R
import com.shihcheeng.hacgcompose.components.ErrorItem
import com.shihcheeng.hacgcompose.components.ErrorScreen
import com.shihcheeng.hacgcompose.components.MagnetDialog
import com.shihcheeng.hacgcompose.components.TriStateScreen
import com.shihcheeng.hacgcompose.components.htmlTransformer.formatNodes
import com.shihcheeng.hacgcompose.components.triState
import com.shihcheeng.hacgcompose.ui.icons.Iconify
import com.shihcheeng.hacgcompose.ui.icons.MdiMagnet
import com.shihcheeng.hacgcompose.utils.extra.margeWith
import com.shihcheeng.hacgcompose.utils.extra.openMagnet

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
    val magnetList by detailViewModel.magnetList.collectAsStateWithLifecycle()
    val rating by detailViewModel.rating.collectAsStateWithLifecycle()

    val lazyState = rememberLazyListState()
    val clipboard = LocalClipboardManager.current
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var showDialog by remember { mutableStateOf(false) }


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
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = stringResource(R.string.see_the_magnet))
                },
                icon = {
                    Icon(
                        imageVector = Iconify.MdiMagnet,
                        contentDescription = stringResource(R.string.see_the_magnet)
                    )
                },
                onClick = {
                    showDialog = true
                },
                expanded = true
            )
        }
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
                    ).margeWith(layoutDirection = LocalLayoutDirection.current, bottom = 82.dp),
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
                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.detail_info_author, it.author, it.time),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        RatingBar(
                            value = rating.rating,
                            style = RatingBarStyle.Fill(
                                activeColor = MaterialTheme.colorScheme.primary,
                                inActiveColor = MaterialTheme.colorScheme.primaryContainer
                            ),
                            onValueChange = {},
                            onRatingChanged = {},
                            modifier = Modifier
                                .height(
                                    with(LocalDensity.current) {
                                        14.sp.toDp()
                                    }
                                )
                                .padding(start = 4.dp),
                            spaceBetween = 2.dp,
                            isIndicator = true,
                            size = with(LocalDensity.current) {
                                14.sp.toDp()
                            }
                        )
                    }
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

    if (showDialog) {
        MagnetDialog(
            list = magnetList,
            dismissRequest = { showDialog = false },
            onOpen = context::openMagnet,
            onCopy = { clipboard.setText(AnnotatedString(it)) }
        )
    }

}