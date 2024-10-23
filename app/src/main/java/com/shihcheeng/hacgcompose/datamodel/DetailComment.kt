package com.shihcheeng.hacgcompose.datamodel

data class DetailComment(
    val name: String,
    val time: String,
    val imageUrl: String,
    val comment: String,
    val vote: String,
    val toSomeone: String
)

data class MainDetailComment(
    val name: String,
    val time: String,
    val imageUrl: String,
    val comment: String,
    val vote: String,
    val list: List<DetailComment>?
)