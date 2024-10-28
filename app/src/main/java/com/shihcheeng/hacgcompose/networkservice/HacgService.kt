package com.shihcheeng.hacgcompose.networkservice

import com.shihcheeng.hacgcompose.utils.ANIME_PAGE
import com.shihcheeng.hacgcompose.utils.ANIME_PAGE_WITH_PAGER
import com.shihcheeng.hacgcompose.utils.ARTICLE_PAGE
import com.shihcheeng.hacgcompose.utils.ARTICLE_PAGE_WITH_PAGER
import com.shihcheeng.hacgcompose.utils.BOOK_URL
import com.shihcheeng.hacgcompose.utils.BOOK_URL_WITH_PAGER
import com.shihcheeng.hacgcompose.utils.COMIC_PAGE
import com.shihcheeng.hacgcompose.utils.COMIC_PAGE_WITH_PAGER
import com.shihcheeng.hacgcompose.utils.DETAIL_URL
import com.shihcheeng.hacgcompose.utils.GAME_URL
import com.shihcheeng.hacgcompose.utils.GAME_URL_WITH_PAGER
import com.shihcheeng.hacgcompose.utils.HOME_PAGE
import com.shihcheeng.hacgcompose.utils.HOME_PAGE_WITH_PAGER
import com.shihcheeng.hacgcompose.utils.OP_URL
import com.shihcheeng.hacgcompose.utils.OP_URL_WITH_PAGER
import org.jsoup.nodes.Document
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HacgService {

    @GET(HOME_PAGE)
    suspend fun getHome(): Document

    @GET(HOME_PAGE_WITH_PAGER)
    suspend fun getHome(@Path("count") count: Int): Document

    @GET(ARTICLE_PAGE)
    suspend fun getArticle(): Document

    @GET(ARTICLE_PAGE_WITH_PAGER)
    suspend fun getArticle(@Path("count") count: Int): Document

    @GET(ANIME_PAGE)
    suspend fun getAnime(): Document

    @GET(ANIME_PAGE_WITH_PAGER)
    suspend fun getAnime(@Path("count") count: Int): Document

    @GET(COMIC_PAGE)
    suspend fun getComic(): Document

    @GET(COMIC_PAGE_WITH_PAGER)
    suspend fun getComic(@Path("count") count: Int): Document

    @GET(DETAIL_URL)
    suspend fun getDetail(@Path("page") path: String): Document

    @GET(GAME_URL)
    suspend fun getGame(): Document

    @GET(GAME_URL_WITH_PAGER)
    suspend fun getGame(@Path("count") count: Int): Document

    @GET(BOOK_URL)
    suspend fun getNovel(): Document

    @GET(BOOK_URL_WITH_PAGER)
    suspend fun getNovel(@Path("count") count: Int): Document

    @GET(OP_URL)
    suspend fun getMusic(): Document

    @GET(OP_URL_WITH_PAGER)
    suspend fun getMusic(@Path("count") count: Int): Document

    @GET(HOME_PAGE)
    suspend fun search(
        @Query("s") word: String,
        @Query("submit") submit: String = "搜索"
    ): Document

    @GET(HOME_PAGE_WITH_PAGER)
    suspend fun search(
        @Path("count") count: Int,
        @Query("s") word: String,
        @Query("submit") submit: String = "搜索"
    ): Document
}