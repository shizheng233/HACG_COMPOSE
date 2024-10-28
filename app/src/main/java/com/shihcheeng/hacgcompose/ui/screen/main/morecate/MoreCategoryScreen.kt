package com.shihcheeng.hacgcompose.ui.screen.main.morecate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.shihcheeng.hacgcompose.ui.screen.main.constants.MainTabModel
import com.shihcheeng.hacgcompose.ui.screen.main.constants.topAppBarColorDetected
import com.shihcheeng.hacgcompose.ui.screen.main.game.GameScreen
import com.shihcheeng.hacgcompose.ui.screen.main.music.MusicScreen
import com.shihcheeng.hacgcompose.ui.screen.main.novel.NovelScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreCategoryScreen(
    modifier: Modifier = Modifier,
    onNav: (String) -> Unit,
    behavior: TopAppBarScrollBehavior
) {
    val tabs = MainTabModel.create()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = tabs::size)
    val scoped = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        PrimaryTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            containerColor = topAppBarColorDetected(behavior)
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = {
                        scoped.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = stringResource(tab.title))
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pagerIndex ->
            when (pagerIndex) {
                0 -> GameScreen(onNav = onNav)
                1 -> NovelScreen(onNav = onNav)
                2 -> MusicScreen(onNav = onNav)
            }
        }
    }
}
