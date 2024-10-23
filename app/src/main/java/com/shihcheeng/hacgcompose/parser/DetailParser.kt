package com.shihcheeng.hacgcompose.parser

import com.shihcheeng.hacgcompose.datamodel.DetailTitleDataModel
import com.shihcheeng.hacgcompose.networkservice.HacgService
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class DetailParser @Inject constructor(
    private val service: HacgService
) {

    suspend fun parserDetailBox(id: String): Element = service.getDetail(id).body()

    suspend fun parserTitle(element: Element): DetailTitleDataModel = suspendCoroutine {
        val title = element.select("#content > article > header > h1")
            .first()?.text().orEmpty()
        val time = element.select("#content > article > header > div > a > time")
            .first()?.text().orEmpty()
        val author = element
            .select("#content > article > header > div > span.by-author > span.author.vcard > a")
            .first()?.text().orEmpty()
        it.resume(DetailTitleDataModel(title, time, author))
    }

    suspend fun parserContentElement(element: Element): List<Node> = suspendCoroutine {
        val content = element.select("#content > article > div.entry-content")
        it.resume(content.first()?.childNodes() ?: emptyList())
    }

}