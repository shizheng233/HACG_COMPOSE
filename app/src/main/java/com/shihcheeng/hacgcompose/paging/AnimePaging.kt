package com.shihcheeng.hacgcompose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.parser.AnimeParser

class AnimePaging(private val parser: AnimeParser) : PagingSource<Int, MainDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, MainDataModel>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MainDataModel> {
        val key = params.key ?: 1
        return try {
            val data = parser.parser(count = key)
            LoadResult.Page(data, null, key + 1)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}