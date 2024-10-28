package com.shihcheeng.hacgcompose.repository

import androidx.paging.PagingSource
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.paging.MusicPaging
import com.shihcheeng.hacgcompose.parser.MusicParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicRepository @Inject constructor(private val parser: MusicParser) : PagingRepository {

    override fun providerPaging(): PagingSource<Int, MainDataModel> {
        return MusicPaging(parser)
    }
}