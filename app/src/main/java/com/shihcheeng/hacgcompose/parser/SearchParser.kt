package com.shihcheeng.hacgcompose.parser

import com.shihcheeng.hacgcompose.networkservice.HacgService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.jsoup.nodes.Document
import javax.inject.Singleton


class SearchParser @AssistedInject constructor(
    override val service: HacgService,
    @Assisted val word: String
) : Parser {

    override suspend fun parserSinglePager(): Document {
        return service.search(word)
    }

    override suspend fun parserPagedPager(count: Int): Document {
        return service.search(count, word)
    }

    companion object {

        @Singleton
        @AssistedFactory
        interface CreateSearchParser {

            fun create(word: String): SearchParser

        }

    }

}