package com.example.composepicsartsample.data.entity

import com.google.gson.annotations.SerializedName

data class VotingCard(
    @SerializedName("data")
    val votingItems: List<VotingItem>
)

data class VotingItem(
    @SerializedName("display_name")
    val name: String,
    val timeLeft: String = "",
    @SerializedName("cover")
    val coverImageUrl: String
)

const val CONTESTS_VOTING = "contests_voting"