package com.shihcheeng.hacgcompose.parser

import com.shihcheeng.hacgcompose.datamodel.CategoryModel
import com.shihcheeng.hacgcompose.datamodel.SearchItemDataModel
import com.shihcheeng.hacgcompose.datamodel.TagModel
import com.shihcheeng.hacgcompose.networkservice.HacgService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Singleton
import kotlin.coroutines.suspendCoroutine


class SearchParser @AssistedInject constructor(
    private val service: HacgService,
    @Assisted val word: String
) {

    suspend fun parser(count: Int): List<SearchItemDataModel> {
        val docs = if (count == 1) service.search(word) else service.search(count, word)
        return suspendCoroutine {
            val articles = docs.body().select("#content").first()
                ?.getElementsByTag("article")
                ?: error("没有找到内容")
            val list = articles.filter { x ->
                val header = x.getElementsByClass("entry-header")
                    .first()?.getElementsByTag("h1")?.first()?.getElementsByTag("a")?.first()
                header != null
            }.map { x ->
                val header = x.getElementsByClass("entry-header")
                    .first()?.getElementsByTag("h1")?.first()?.getElementsByTag("a")?.first()
                    ?: throw NoSuchElementException("Not find entry-header.")
                val title = header.text()
                val href = header.attr("href")
                val entryMeta = x.getElementsByClass("entry-meta")
                    .first() ?: throw NoSuchElementException("No entry meta found.")
                val time = entryMeta.getElementsByTag("a").first()?.text()
                val publisher = entryMeta.getElementsByClass("by-author").first()
                    ?.getElementsByClass("author")?.first()
                    ?.getElementsByTag("a")?.first()?.text()
                val content = x.getElementsByClass("entry-summary").first()
                val description = content?.getElementsByTag("p")
                    ?.first()?.removeClass("more-link")
                    ?.apply {
                        getElementsByTag("a").last()?.remove()
                    }?.text()?.let {
                        if (!it.endsWith("。")) {
                            it.plus("。")
                        } else {
                            it
                        }
                    } ?: throw NoSuchElementException("没有找到条目中的简介文本！")
                val footerBox = x.getElementsByTag("footer").first()
                val catLinks = footerBox?.getElementsByClass("cat-links")
                    ?.first()
                    ?.getElementsByTag("a")
                    ?: throw NoSuchElementException("No cate link found.")
                val category = CategoryModel(
                    name = catLinks.text(),
                    href = catLinks.attr("href")
                )
                val tagBox = footerBox.getElementsByClass("tag-links").first()
                    ?.getElementsByTag("a")
                val tagModels = tagBox?.map {
                    TagModel(name = it.text(), href = it.attr("href"))
                } ?: emptyList()
                SearchItemDataModel(
                    title = title,
                    description = description,
                    tags = tagModels,
                    time = time.orEmpty(),
                    publisher = publisher.orEmpty(),
                    category = category.name,
                    href = href
                )
            }
            it.resumeWith(Result.success(list))
        }
    }

    companion object {

        @Singleton
        @AssistedFactory
        interface CreateSearchParser {

            fun create(word: String): SearchParser

        }

    }

}