package com.shihcheeng.hacgcompose.datamodel

data class MainDetailComment(
    val name: String,
    val time: String,
    val imageUrl: String,
    val comment: String,
    val vote: String,
    val reply: String? = null,
    /**
     * 层级。
     */
    val level: Int = 1,
    val isStickyTop: Boolean = false,
    val isByAuthor: Boolean = false
)