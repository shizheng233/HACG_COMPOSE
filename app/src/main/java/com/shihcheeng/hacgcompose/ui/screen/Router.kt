package com.shihcheeng.hacgcompose.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shihcheeng.hacgcompose.ui.screen.RouterName.Detail.toDetailScreen
import com.shihcheeng.hacgcompose.ui.screen.RouterName.Search.toSearch
import com.shihcheeng.hacgcompose.ui.screen.detail.DetailScreen
import com.shihcheeng.hacgcompose.ui.screen.main.MainScreen
import com.shihcheeng.hacgcompose.ui.screen.search.SearchScreen
import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut
import soup.compose.material.motion.animation.rememberSlideDistance

@Composable
fun Router(
    navHostController: NavHostController = rememberNavController(),
    slide: Int = rememberSlideDistance()
) {

    NavHost(
        navController = navHostController,
        startDestination = RouterName.Main.name,
        popExitTransition = { materialSharedAxisXOut(forward = false, slideDistance = slide) },
        popEnterTransition = { materialSharedAxisXIn(forward = false, slideDistance = slide) },
        enterTransition = { materialSharedAxisXIn(forward = true, slideDistance = slide) },
        exitTransition = { materialSharedAxisXOut(forward = true, slideDistance = slide) }
    ) {
        composable(RouterName.Main.name) {
            MainScreen(
                onSearch = { navHostController.toSearch() }
            ) {
                navHostController.toDetailScreen(it)
            }
        }

        composable(route = RouterName.Detail.name + "/{detailId}") {
            DetailScreen {
                navHostController.popBackStack()
            }
        }

        composable(route = RouterName.Search.name) {
            SearchScreen()
        }

    }

}


sealed class RouterName(val name: String) {

    data object Main : RouterName("MAIN")

    data object Detail : RouterName("DETAIL") {

        fun NavHostController.toDetailScreen(name: String) {
            navigate(this@Detail.name + "/" + name)
        }

    }

    data object Search : RouterName("SEARCH") {
        fun NavHostController.toSearch() {
            navigate(this@Search.name)
        }
    }

}