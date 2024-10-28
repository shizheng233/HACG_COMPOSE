package com.shihcheeng.hacgcompose.repository

import androidx.paging.PagingSource
import com.shihcheeng.hacgcompose.datamodel.MainDataModel
import com.shihcheeng.hacgcompose.paging.GamePaging
import com.shihcheeng.hacgcompose.parser.GameParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(private val parser: GameParser) : PagingRepository {

    override fun providerPaging(): PagingSource<Int, MainDataModel> {
        return GamePaging(parser)
    }
}