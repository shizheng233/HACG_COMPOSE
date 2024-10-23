package com.shihcheeng.hacgcompose.ui.screen.main.constants

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.shihcheeng.hacgcompose.R

data class MainNavModel(
    @StringRes val name: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val pressIcon: Int
) {

    companion object {
        @Composable
        fun create(): List<MainNavModel> = remember {
            listOf(
                MainNavModel(
                    name = R.string.home,
                    icon = R.drawable.outline_home_24,
                    pressIcon = R.drawable.baseline_home_24
                ),
                MainNavModel(
                    name = R.string.article,
                    icon = R.drawable.outline_article_24,
                    pressIcon = R.drawable.baseline_article_24
                ),
                MainNavModel(
                    name = R.string.movies,
                    icon = R.drawable.outline_movie_24,
                    pressIcon = R.drawable.baseline_movie_24
                ),
                MainNavModel(
                    name = R.string.book,
                    icon = R.drawable.outline_menu_book_24,
                    pressIcon = R.drawable.baseline_menu_book_24
                ),
//                MainNavModel(
//                    name = R.string.more,
//                    icon = R.drawable.baseline_more_horiz_24,
//                    pressIcon = R.drawable.baseline_more_horiz_24
//                )
            )
        }
    }

}