package com.shihcheeng.hacgcompose.utils

import org.jsoup.nodes.Element


const val BASE_URL = "https://www.hacg.icu/"
const val HOME_PAGE = "wp/"
const val HOME_PAGE_WITH_PAGER = "wp/page/{count}"
const val ARTICLE_PAGE = "wp/age.html"
const val ARTICLE_PAGE_WITH_PAGER = "wp/age.html/page/{count}"
const val ANIME_PAGE = "wp/anime.html"
const val ANIME_PAGE_WITH_PAGER = "wp/anime.html/page/{count}"
const val COMIC_PAGE = "wp/comic.html"
const val COMIC_PAGE_WITH_PAGER = "wp/comic.html/page/{count}"
const val DETAIL_URL = "wp/{page}"
const val COMMENT_LEVEL_2 = "wpd_comment_level-2"
const val COMMENT_LEVEL_3 = "wpd_comment_level-3"
const val COMMENT_LEVEL_4 = "wpd_comment_level-4"
const val COMMENT_LEVEL_5 = "wpd_comment_level-5"

fun commentLevelFor(element: Element?): Int {
    val classesName = element?.classNames() ?: emptyList<String>()
    return when {
        classesName.contains(COMMENT_LEVEL_5) -> 4
        classesName.contains(COMMENT_LEVEL_4) -> 3
        classesName.contains(COMMENT_LEVEL_3) -> 2
        classesName.contains(COMMENT_LEVEL_2) -> 1
        else -> 0
    }
}