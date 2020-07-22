package com.example.composepicsartsample.data

import android.app.Application
import com.example.composepicsartsample.data.entity.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class DataRepository(application: Application) {

    private val gson = GsonBuilder().create()
    private val json: JsonElement =
        JsonParser.parseReader(InputStreamReader(application.assets.open("response.json")))

    fun getChallengeResponse(): List<Any> {
        val result = mutableListOf<Any>()
        json.asJsonObject.get("response").asJsonArray.forEach {
            when (it.asJsonObject.get("type").asString) {
                CONTESTS_ACTIVE -> {
                    result.add(
                        TitleItem(
                            it.asJsonObject.get("title").asString,
                            seeAll = it.asJsonObject.get("header_title")?.asString != null
                        )
                    )
                    result.addAll(
                        gson.fromJson<List<Challenge>>(
                            it.asJsonObject.get("data"),
                            object : TypeToken<List<Challenge>>() {}.type
                        )
                    )
                }
                CONTESTS_ENDED -> {
                    val endedChallengeJson = it.asJsonObject
                    result += TitleItem(
                        "Ended Challenges",
                        seeAll = endedChallengeJson.get("header_title")?.asString != null
                    )
                    endedChallengeJson["data"].asJsonArray.forEachIndexed { index, jsonElement ->
                        result += gson.fromJson(jsonElement.asJsonObject, EndedChallenge::class.java)
                    }
                }
                CONTESTS_VOTING -> {
                    result += TitleItem(
                        it.asJsonObject.get("title").asString,
                        seeAll = it.asJsonObject.get("header_title")?.asString != null
                    )
                    result += gson.fromJson(
                        it.asJsonObject,
                        VotingCard::class.java
                    )
                }
            }
        }
        return result
    }

}