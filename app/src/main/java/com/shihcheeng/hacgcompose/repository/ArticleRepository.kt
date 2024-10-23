package com.shihcheeng.hacgcompose.repository

import androidx.paging.PagingSource
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.paging.ArticlePaging
import com.shihcheeng.hacgcompose.parser.ArticleParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val parser: ArticleParser
) : PagingRepository {

    override fun providerPaging(): PagingSource<Int, MainDataModel> {
        return ArticlePaging(parser)
    }
}