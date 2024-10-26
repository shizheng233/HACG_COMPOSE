package com.shihcheeng.hacgcompose.parser

import android.util.Log
import androidx.core.net.toUri
import com.shihcheeng.hacgcompose.datamodel.DetailTitleDataModel
import com.shihcheeng.hacgcompose.datamodel.MainDetailComment
import com.shihcheeng.hacgcompose.networkservice.HacgService
import com.shihcheeng.hacgcompose.utils.commentLevelFor
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private const val TAG = "DetailParser"

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
                .also {
                    elementBox.classNames().forEach { name ->
                        Log.i(TAG, "parserComments: $name")
                    }
                }
            val header = commentBody?.getElementsByClass("wpd-comment-header")?.first()
            val imageUrl = header
                ?.getElementsByClass("wpd-avatar")?.first()
                ?.let {
                    if (it.getElementsByTag("a").first() != null) {
                        it.getElementsByTag("a").first()
                    } else {
                        it
                    }
                }
                ?.getElementsByTag("img")
                ?.first()
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
            val bottom = header?.getElementsByClass("wpd-uinfo-bottom")?.first()
            val time = bottom?.getElementsByClass("wpd-comment-date")
                ?.first()
                ?.text()
            val replySomeOne = bottom?.getElementsByClass("wpd-reply-to")?.first()
                ?.getElementsByTag("a")?.first()?.text()
            val vote = elementBox.getElementsByClass("wpd-comment-footer")
                .first()?.getElementsByClass("wpd-vote")?.first()
                ?.getElementsByClass("wpd-vote-result")?.first()
                ?.text()
            val commentContent = elementBox.getElementsByClass("wpd-comment-text")
                .first()?.text()
            MainDetailComment(
                name = name.orEmpty(),
                time = time.orEmpty(),
                imageUrl = imageUrl.orEmpty(),
                comment = commentContent.orEmpty(),
                vote = vote.orEmpty(),
                level = commentLevelFor(elementBox),
                reply = replySomeOne
            )
        }
        cont.resume(list)
    }

}