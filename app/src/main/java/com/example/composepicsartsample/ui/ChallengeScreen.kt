package com.example.composepicsartsample.ui

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.foundation.lazy.LazyRowItems
import androidx.ui.layout.*
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.unit.dp
import com.example.composepicsartsample.data.entity.Challenge
import com.example.composepicsartsample.data.entity.EndedChallenge
import com.example.composepicsartsample.data.entity.TitleItem
import com.example.composepicsartsample.data.entity.VotingCard
import com.example.composepicsartsample.ui.components.ChallengeCard
import com.example.composepicsartsample.ui.components.EndedChallengeCard
import com.example.composepicsartsample.ui.components.Title
import com.example.composepicsartsample.ui.components.VotingColumn

@Composable
fun ChallengeScreen(data: List<Any>, modifier: Modifier) {
    LazyColumnItems(items = data, modifier = modifier) {
        when (it) {
            is TitleItem -> {
                if (it.text == "Ended Challenges") {
                    Spacer(modifier = Modifier.padding(16.dp))
                }
                Title(
                    title = it.text,
                    seeAll = it.seeAll,
                    subtitle = it.subTitle
                )
            }
            is VotingCard -> {
                LazyRowItems(
                    items = it.votingItems,
                    modifier = Modifier.fillMaxWidth().preferredHeight(176.dp)
                ) { votingItem ->
                    VotingColumn(
                        votingItem = votingItem,
                        modifier = Modifier.padding(
                            InnerPadding(
                                start = if (it.votingItems.indexOf(votingItem) == 0) 16.dp else 8.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = if (it.votingItems.indexOf(votingItem) == it.votingItems.size - 1) 16.dp else 8.dp
                            )
                        )
                    )
                }
            }
            is Challenge -> ChallengeCard(
                challenge = it,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            )
            is EndedChallenge -> {
                EndedChallengeCard(it)
            }
        }
    }
}

@Composable
fun LoadingHomeScreen() {
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        CircularProgressIndicator()
    }
}