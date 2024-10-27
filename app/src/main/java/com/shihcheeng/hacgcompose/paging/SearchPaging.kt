package com.shihcheeng.hacgcompose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shihcheeng.hacgcompose.datamodel.SearchItemDataModel
import com.shihcheeng.hacgcompose.parser.SearchParser

class SearchPaging(private val parser: SearchParser) : PagingSource<Int, SearchItemDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, SearchItemDataModel>): Int {
        return 1
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, SearchItemDataModel> {
        val key = params.key ?: 1
        return try {
            val data = parser.parser(key)
            LoadResult.Page(data, null, key + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}