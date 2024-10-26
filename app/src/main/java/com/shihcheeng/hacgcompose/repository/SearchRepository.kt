package com.shihcheeng.hacgcompose.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.paging.SearchPaging
import com.shihcheeng.hacgcompose.parser.SearchParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val factory: SearchParser.Companion.CreateSearchParser
) {

    fun pagingSearch(word: String) = Pager(config = PagingConfig(1)) {
        providerPaging(word)
    }.flow

    private fun providerPaging(word: String): PagingSource<Int, MainDataModel> {
        return SearchPaging(parser = factory.create(word))
    }

}