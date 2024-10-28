package com.shihcheeng.hacgcompose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.parser.NovelParser

class NovelPaging private constructor(
    private val novelParser: NovelParser
) : PagingSource<Int, MainDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, MainDataModel>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MainDataModel> {
        val key = params.key ?: 1
        return try {
            val data = novelParser.parser(key)
            LoadResult.Page(data, null, key + 1)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    companion object {

        /**
         * 创造[NovelPaging]。之所以这么写是因为我无聊。
         *
         * @param novelParser 提供[NovelParser]
         */
        fun create(novelParser: NovelParser): NovelPaging {
            return NovelPaging(novelParser)
        }

    }

}