package com.shihcheeng.hacgcompose.repository

import com.shihcheeng.hacgcompose.datamodel.DetailTitleDataModel
import com.shihcheeng.hacgcompose.datamodel.MainDetailComment
import com.shihcheeng.hacgcompose.parser.DetailParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(
    private val parser: DetailParser
) {

    suspend fun data(id: String): Element = parser.parserDetailBox(id)

    suspend fun parserTitle(element: Element): DetailTitleDataModel =
        withContext(Dispatchers.Default) {
            parser.parserTitle(element)
        }

    suspend fun parserBody(element: Element): List<Node> = withContext(Dispatchers.Default) {
        parser.parserContentElement(element)
    }

    suspend fun parserComments(
        element: Element
    ): List<MainDetailComment>? = withContext(Dispatchers.Default) {
        parser.parserComments(element)
    }

}