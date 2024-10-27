package com.shihcheeng.hacgcompose.ui.screen.main.constants

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.shihcheeng.hacgcompose.R

data class MainTabModel(
    @StringRes
    val title: Int
) {

    companion object {

        @Composable
        fun create() = remember {
            listOf(
                MainTabModel(R.string.game),
                MainTabModel(R.string.book),
                MainTabModel(R.string.music)
            )
        }

    }

}