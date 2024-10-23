package com.shihcheeng.hacgcompose.datamodel

data class MainDataModel(
    val title: String,
    val description: String,
    val tags: List<TagModel>,
    val time: String,
    val publisher: String,
    val category: CategoryModel,
    val imageURl: String,
    val href: String
)

data class TagModel(val name: String, val href: String)
data class CategoryModel(val name: String, val href: String)