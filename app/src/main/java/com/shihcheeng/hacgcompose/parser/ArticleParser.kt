package com.shihcheeng.hacgcompose.parser

import com.shihcheeng.hacgcompose.networkservice.HacgService
import org.jsoup.nodes.Document
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleParser @Inject constructor(
    override val service: HacgService
) : Parser {

    override suspend fun parserSinglePager(): Document {
        return service.getArticle()
    }

    override suspend fun parserPagedPager(count: Int): Document {
        return service.getArticle(count)
    }
}