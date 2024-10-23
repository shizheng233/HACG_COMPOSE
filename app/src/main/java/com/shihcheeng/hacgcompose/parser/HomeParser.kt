package com.shihcheeng.hacgcompose.parser

import com.shihcheeng.hacgcompose.datamodel.CategoryModel
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.datamodel.TagModel
import com.shihcheeng.hacgcompose.networkservice.HacgService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeParser @Inject constructor(
    private val service: HacgService
) {

    suspend fun parser(
        count: Int
    ): List<MainDataModel> {
        val innerDoc = if (count == 1) service.getHome() else service.getHome(count)
        val articles =
            innerDoc.body().select("#content").first()?.getElementsByTag("article") ?: emptyList()
        return articles
            .filter { x ->
                val header = x.getElementsByClass("entry-header")
                    .first()?.getElementsByTag("h1")?.first()?.getElementsByTag("a")?.first()
                header != null
            }
            .map { x ->
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
                val content = x.getElementsByClass("entry-content").first()
                val description = content?.getElementsByTag("p")?.first()
                    ?.apply {
                        getElementsByTag("a").last()?.remove()
                    }?.text()?.let {
                        if (!it.endsWith("。")) {
                            it.plus("。")
                        } else {
                            it
                        }
                    } ?: throw NoSuchElementException("No content")
                val imageUrl = content.getElementsByTag("p").first()
                    ?.getElementsByTag("img")?.first()
                    ?.attr("src")
                val footerBox = x.getElementsByTag("footer").first()
                val catLinks = footerBox?.getElementsByClass("cat-links")
                    ?.first()
                    ?.getElementsByTag("a") ?: throw NoSuchElementException("No cate link found.")
                val category = CategoryModel(
                    name = catLinks.text(),
                    href = catLinks.attr("href")
                )
                val tagBox = footerBox.getElementsByClass("tag-links").first()
                    ?.getElementsByTag("a")
                val tagModels = tagBox?.map {
                    TagModel(name = it.text(), href = it.attr("href"))
                } ?: emptyList()
                MainDataModel(
                    title = title,
                    description = description,
                    tags = tagModels,
                    time = time.orEmpty(),
                    publisher = publisher.orEmpty(),
                    category = category,
                    imageURl = imageUrl.orEmpty(),
                    href = href
                )
            }

    }

}