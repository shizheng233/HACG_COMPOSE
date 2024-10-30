package com.shihcheeng.hacgcompose.datamodel

data class PagedDataModel<T>(
    val current: Int,
    val total: Int,
    val data: T
)