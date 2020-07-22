package com.example.composepicsartsample.data.entity

import com.google.gson.annotations.SerializedName

data class Challenge(
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("min_desc")
    val minDesc: String,
    @SerializedName("cover")
    val coverUrl: String,
    @SerializedName("owner")
    val owner: User,
    @SerializedName("prize")
    val prize : Prize
)

data class EndedChallenge(
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("min_desc")
    val minDesc: String,
    @SerializedName("photos")
    val winnerItems : List<WinnerItemModel>
)

data class TitleItem(val text : String, val subTitle : String? = null, val seeAll : Boolean = false)

data class Prize(@SerializedName("text") val text : String)

const val CONTESTS_ACTIVE = "contests_active"
const val CONTESTS_ENDED = "contests_ended"