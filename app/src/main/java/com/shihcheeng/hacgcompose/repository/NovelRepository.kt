package com.shihcheeng.hacgcompose.repository

import androidx.paging.PagingSource
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.paging.NovelPaging
import com.shihcheeng.hacgcompose.parser.NovelParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NovelRepository @Inject constructor(
    private val novelParser: NovelParser
) : PagingRepository {

    override fun providerPaging(): PagingSource<Int, MainDataModel> {
        return NovelPaging.create(novelParser)
    }
}