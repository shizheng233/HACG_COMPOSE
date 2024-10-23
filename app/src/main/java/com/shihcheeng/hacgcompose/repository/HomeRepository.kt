package com.shihcheeng.hacgcompose.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.shihcheeng.hacgcompose.paging.HomePaging
import com.shihcheeng.hacgcompose.parser.HomeParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val parser: HomeParser
) {

    fun load() = Pager(config = PagingConfig(1)) {
        HomePaging(parser)
    }.flow

}