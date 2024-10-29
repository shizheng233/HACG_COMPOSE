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
const val GAME_URL = "wp/game.html"
const val GAME_URL_WITH_PAGER = "wp/game.html/page/{count}"
const val BOOK_URL = "wp/book.html"
const val BOOK_URL_WITH_PAGER = "wp/book.html/page/{count}"
const val OP_URL = "wp/op.html"
const val OP_URL_WITH_PAGER = "wp/op.html/page/{count}"
const val COMMENT_LEVEL_2 = "wpd_comment_level-2"
const val COMMENT_LEVEL_3 = "wpd_comment_level-3"
const val COMMENT_LEVEL_4 = "wpd_comment_level-4"
const val COMMENT_LEVEL_5 = "wpd_comment_level-5"
const val COMMENT_BY_POST_AUTHOR = "bypostauthor"
const val COMMENT_IS_STICKY_COMMENT = "wpd-sticky-comment"
/*
https://www.hacg.icu
https://www.llss.icu
https://www.hacg.mov
 */

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

fun Element?.detectCommentIsByAuthor(): Boolean {
    val classesName = this?.classNames() ?: emptyList<String>()
    return when {
        classesName.contains(COMMENT_BY_POST_AUTHOR) -> true
        else -> false
    }
}

fun Element?.detectCommentIsSticky(): Boolean {
    val classesName = this?.classNames() ?: emptyList<String>()
    return when {
        classesName.contains(COMMENT_IS_STICKY_COMMENT) -> true
        else -> false
    }
}