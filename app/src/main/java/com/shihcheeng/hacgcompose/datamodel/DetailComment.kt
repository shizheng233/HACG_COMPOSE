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
) {

    constructor(
        name: String?,
        time: String?,
        imageUrl: String?,
        comment: String?,
        vote: String?,
        list: List<DetailComment>?
    ) : this(
        name.orEmpty(),
        time.orEmpty(),
        imageUrl.orEmpty(),
        comment.orEmpty(),
        vote.orEmpty(),
        list
    )

}