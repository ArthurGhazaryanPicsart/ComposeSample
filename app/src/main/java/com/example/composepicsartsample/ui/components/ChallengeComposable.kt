package com.example.composepicsartsample.ui.components

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.drawBackground
import androidx.ui.foundation.lazy.LazyRowItems
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.HorizontalGradient
import androidx.ui.graphics.LinearGradient
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.composepicsartsample.data.entity.*
import com.example.composepicsartsample.ui.ComposePicsartSampleTheme
import com.example.composepicsartsample.ui.PicassoImage

@Composable
fun ChallengeCard(challenge: Challenge, modifier: Modifier) {
    Column(modifier = modifier) {
        Row(verticalGravity = Alignment.Top, modifier = Modifier.padding(bottom = 8.dp)) {
            PicassoImage(
                url = challenge.owner.photo.getLowUrl(),
                modifier = Modifier.preferredWidth(40.dp).aspectRatio(1f)
                    .clip(
                        CircleShape
                    )
            )
            Title(
                title = challenge.owner.username,
                seeAll = false,
                subtitle = challenge.minDesc,
                modifier = Modifier.padding(start = 16.dp),
                textSize = 14.sp,
                subTitleTextSize = 12.sp
            )
        }
        PicassoImage(
            url = challenge.coverUrl.getMiddleUrl(),
            modifier = Modifier.fillMaxWidth().aspectRatio(1.703826955f).clip(
                RoundedCornerShape(4.dp)
            ).clickable(onClick = {})
        )
        Row(
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
            verticalGravity = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = challenge.displayName,
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = challenge.prize.text,
                    style = TextStyle(Color.Gray, fontWeight = FontWeight.Normal, fontSize = 14.sp)
                )
            }
            Box(
                modifier = Modifier.clickable(onClick = {}).clip(RoundedCornerShape(2.dp))
                    .drawBackground(
                        LinearGradient(
                            listOf(
                                Color(android.graphics.Color.parseColor("#FF0370C9")),
                                Color(android.graphics.Color.parseColor("#FF00C4F5"))
                            ),
                            0f,
                            50f,
                            200f,
                            0f
                        )
                    )
            ) {
                Text(
                    text = "MORE",
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    style = TextStyle(fontWeight = FontWeight.Black)
                )
            }
        }
    }
}

@Composable
fun EndedChallengeCard(endedChallenge: EndedChallenge) {
    Column {
        Title(
            title = endedChallenge.displayName,
            subtitle = endedChallenge.minDesc, seeAll = false
        )
        LazyRowItems(
            items = endedChallenge.winnerItems,
            modifier = Modifier.preferredHeight(226.dp)
        ) { winnerItem ->
            WinnerItem(
                item = winnerItem,
                modifier = Modifier.padding(
                    InnerPadding(
                        start = if (endedChallenge.winnerItems.indexOf(winnerItem) == 0) 16.dp else 8.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        end = if (endedChallenge.winnerItems.indexOf(winnerItem) == endedChallenge.winnerItems.size - 1) 16.dp else 8.dp
                    )
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengeCardPreview() {
    ComposePicsartSampleTheme {
        ChallengeCard(
            challenge = Challenge(
                "Test Photo NoteBook",
                minDesc = "Photography challenge",
                coverUrl = PHOTO_URL,
                owner = User(name = "test_user", photo = PHOTO_URL, username = "test-user"),
                prize = Prize("Get promoted")
            ), modifier = Modifier.padding(16.dp)
        )
    }
}