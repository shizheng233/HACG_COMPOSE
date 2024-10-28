package com.shihcheeng.hacgcompose.parser

import com.shihcheeng.hacgcompose.networkservice.HacgService
import org.jsoup.nodes.Document
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NovelParser @Inject constructor(override val service: HacgService) : Parser {

    override suspend fun parserSinglePager(): Document {
        return service.getNovel()
    }

    override suspend fun parserPagedPager(count: Int): Document {
        return service.getNovel(count)
    }
}