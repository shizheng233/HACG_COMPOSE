package com.shihcheeng.hacgcompose.parser

import androidx.core.net.toUri
import com.shihcheeng.hacgcompose.datamodel.DetailComment
import com.shihcheeng.hacgcompose.datamodel.DetailTitleDataModel
import com.shihcheeng.hacgcompose.datamodel.MainDetailComment
import com.shihcheeng.hacgcompose.networkservice.HacgService
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.select.Elements
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

    suspend fun parserComments(
        element: Element
    ): List<MainDetailComment>? = suspendCoroutine { cont ->
        val comments = element.select("#wpd-threads > div.wpd-thread-list").first()
            ?.getElementsByClass("comment")
        val list = comments?.map { elementBox ->
            val commentBody = elementBox.getElementsByClass("wpd-comment-wrap").first()
            val header = commentBody?.getElementsByClass("wpd-comment-header")?.first()
            val imageUrl = header
                ?.getElementsByClass("wpd-avatar")?.first()
                ?.let {
                    if (it.getElementsByTag("a").first() != null) {
                        it.getElementsByTag("a").first()
                    } else {
                        it
                    }
                }?.getElementsByTag("img")?.first()
                ?.attr("src")?.let {
                    if (it.toUri().scheme != null) {
                        it
                    } else {
                        "https:$it"
                    }
                }
            val name = header
                ?.getElementsByClass("wpd-user-info")?.first()
                ?.getElementsByClass("wpd-uinfo-top")?.first()
                ?.getElementsByClass("wpd-comment-author")?.first()
                ?.text()
            val time = header?.getElementsByClass("wpd-uinfo-bottom")
                ?.first()?.getElementsByClass("wpd-comment-date")?.first()
                ?.text()
            val vote = elementBox.getElementsByClass("wpd-comment-footer")
                .first()?.getElementsByClass("wpd-vote")?.first()
                ?.getElementsByClass("wpd-vote-result")?.first()
                ?.text()
            val commentContent = elementBox.getElementsByClass("wpd-comment-text")
                .first()?.text()
            MainDetailComment(
                name.orEmpty(),
                time.orEmpty(),
                imageUrl.orEmpty(),
                commentContent.orEmpty(),
                vote.orEmpty(),
                retryShaftComments(elementBox.getElementsByClass("wpd_comment_level-2"))
            )
        }
        cont.resume(list)
    }

    private fun retryShaftComments(elements: Elements?): List<DetailComment>? {
        if (elements == null) return null
        return elements.map { x ->
            val header = x.getElementsByClass("wpd-comment-header").first()
            val imageUrl = header
                ?.getElementsByClass("wpd-avatar")?.first()
                ?.getElementsByTag("img")?.first()
                ?.attr("src")
            val name = header
                ?.getElementsByClass("wpd-user-info")?.first()
                ?.getElementsByClass("wpd-uinfo-top")?.first()
                ?.getElementsByClass("wpd-comment-author")?.first()
                ?.getElementsByTag("a")?.first()?.text()
            val toSomeone = header?.getElementsByClass("wpd-uinfo-bottom")
                ?.first()?.getElementsByClass("wpd-reply-to")?.first()
                ?.getElementsByTag("a")?.first()?.text()
            //
            val time = header?.getElementsByClass("wpd-uinfo-bottom")
                ?.first()?.getElementsByClass("wpd-comment-date")?.first()
                ?.text()
            val vote = x.getElementsByClass("wpd-comment-footer")
                .first()?.getElementsByClass("wpd-vote")?.first()
                ?.getElementsByClass("wpd-vote-result")?.first()
                ?.text()
            val commentContent = x.getElementsByClass("wpd-comment-text").text()
            DetailComment(
                name = name.orEmpty(),
                time = time.orEmpty(),
                imageUrl = imageUrl.orEmpty(),
                comment = commentContent,
                vote = vote.orEmpty(),
                toSomeone = toSomeone.orEmpty()
            )
        }
    }

}