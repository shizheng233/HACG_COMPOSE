package com.shihcheeng.hacgcompose.ui.screen.main.morecate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.shihcheeng.hacgcompose.ui.screen.main.constants.MainTabModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreCategoryScreen(
    modifier: Modifier = Modifier
) {
    val (selected, onSelected) = rememberSaveable {
        mutableIntStateOf(0)
    }
    val tabs = MainTabModel.create()
    val pagerState = rememberPagerState(pageCount = tabs::size)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        PrimaryScrollableTabRow(
            selectedTabIndex = selected,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = index == selected,
                    onClick = { onSelected(index) },
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
                0 -> {}
                1 -> {}
                2 -> {}
            }
        }
    }
}
