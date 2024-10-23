package com.shihcheeng.hacgcompose.networkservice

import com.shihcheeng.hacgcompose.utils.ANIME_PAGE
import com.shihcheeng.hacgcompose.utils.ANIME_PAGE_WITH_PAGER
import com.shihcheeng.hacgcompose.utils.ARTICLE_PAGE
import com.shihcheeng.hacgcompose.utils.ARTICLE_PAGE_WITH_PAGER
import com.shihcheeng.hacgcompose.utils.COMIC_PAGE
import com.shihcheeng.hacgcompose.utils.COMIC_PAGE_WITH_PAGER
import com.shihcheeng.hacgcompose.utils.DETAIL_URL
import com.shihcheeng.hacgcompose.utils.HOME_PAGE
import com.shihcheeng.hacgcompose.utils.HOME_PAGE_WITH_PAGER
import org.jsoup.nodes.Document
import retrofit2.http.GET
import retrofit2.http.Path

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
}