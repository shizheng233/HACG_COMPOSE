package com.shihcheeng.hacgcompose.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.shihcheeng.hacgcompose.R
import com.shihcheeng.hacgcompose.components.AnimatedPager
import com.shihcheeng.hacgcompose.paging.ComicPaging
import com.shihcheeng.hacgcompose.ui.screen.main.anime.AnimeScreen
import com.shihcheeng.hacgcompose.ui.screen.main.article.ArticleScreen
import com.shihcheeng.hacgcompose.ui.screen.main.comic.ComicScreen
import com.shihcheeng.hacgcompose.ui.screen.main.constants.MainNavModel
import com.shihcheeng.hacgcompose.ui.screen.main.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNav: (String) -> Unit
) {
    val (selectedIndex, onSelected) = rememberSaveable { mutableIntStateOf(0) }
    val navModels = MainNavModel.create()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(navModels[selectedIndex].name))
                },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_search_24),
                            contentDescription = stringResource(R.string.input_search_text)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                navModels.forEachIndexed { index, model ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick = { onSelected(index) },
                        icon = {
                            Icon(
                                painter = painterResource(id = if (index == selectedIndex) model.pressIcon else model.icon),
                                contentDescription = stringResource(model.name)
                            )
                        },
                        label = {
                            Text(text = stringResource(model.name))
                        },
                        alwaysShowLabel = true
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        AnimatedPager(
            selected = selectedIndex,
            contentsPadding = paddingValues,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            when (it) {
                0 -> HomeScreen(onNav = onNav)
                1 -> ArticleScreen()
                2 -> AnimeScreen()
                3 -> ComicScreen()
            }
        }
    }
}