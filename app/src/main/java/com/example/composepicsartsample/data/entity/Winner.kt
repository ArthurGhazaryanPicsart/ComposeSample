package com.example.composepicsartsample.data.entity

import com.google.gson.annotations.SerializedName

data class WinnerItemModel(
    @SerializedName("url")
    val url: String,
    @SerializedName("user")
    val user: User,
    val place: Int = 0
)

data class User(
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String
)

fun String.getLowUrl(): String {
    return "${this.removeSuffix("?a=b")}?r240x240"
}

fun String.getMiddleUrl(): String {
    return "${this.removeSuffix("?a=b")}?r350x350"
}