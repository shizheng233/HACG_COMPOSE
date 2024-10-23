package com.shihcheeng.hacgcompose.repository

import androidx.paging.PagingSource
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.paging.ComicPaging
import com.shihcheeng.hacgcompose.parser.ComicParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComicRepository @Inject constructor(
    private val parser: ComicParser
) : PagingRepository {

    override fun providerPaging(): PagingSource<Int, MainDataModel> {
        return ComicPaging(parser)
    }
}