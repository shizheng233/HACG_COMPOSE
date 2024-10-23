package com.shihcheeng.hacgcompose.repository

import androidx.paging.PagingSource
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.paging.AnimePaging
import com.shihcheeng.hacgcompose.parser.AnimeParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    private val parser: AnimeParser
) : PagingRepository {

    override fun providerPaging(): PagingSource<Int, MainDataModel> {
        return AnimePaging(parser)
    }
}