package com.shihcheeng.hacgcompose.datamodel

data class SearchItemDataModel(
    val title: String,
    val description: String,
    val tags: List<TagModel>,
    val time: String,
    val publisher: String,
    val category: String,
    val href: String
)