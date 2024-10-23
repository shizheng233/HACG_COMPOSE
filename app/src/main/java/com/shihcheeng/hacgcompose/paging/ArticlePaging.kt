package com.shihcheeng.hacgcompose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.parser.ArticleParser

class ArticlePaging(private val parser: ArticleParser) : PagingSource<Int, MainDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, MainDataModel>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MainDataModel> {
        val ket = params.key ?: 1
        return try {
            val data = parser.parser(ket)
            LoadResult.Page(data = data, prevKey = null, nextKey = ket + 1)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}