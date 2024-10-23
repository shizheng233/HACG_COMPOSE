package com.shihcheeng.hacgcompose.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.shihcheeng.hacgcompose.datamodel.MainDataModel

interface PagingRepository {

    fun providerPaging(): PagingSource<Int, MainDataModel>

    fun load() = Pager(config = PagingConfig(1)) {
        providerPaging()
    }.flow

}