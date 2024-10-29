package com.shihcheeng.hacgcompose.datamodel

data class DetailTitleDataModel(
    val title: String,
    val time: String,
    val author: String
)

data class DetailRating(val number: Int, val rating: Float) {

    companion object {
        fun none(): DetailRating = DetailRating(0, 0f)
    }

}