package com.example.composepicsartsample.ui.components

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.composepicsartsample.data.entity.VotingItem
import com.example.composepicsartsample.data.entity.getLowUrl
import com.example.composepicsartsample.ui.PicassoImage

@Composable
fun VotingColumn(votingItem: VotingItem, modifier: Modifier) {
    Column(
        horizontalGravity = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        VotingImage(
            url = votingItem.coverImageUrl.getLowUrl(),
            modifier = Modifier.preferredWidth(120.dp).aspectRatio(1f)
        )
        Text(
            text = votingItem.name,
            maxLines = 1,
            modifier = Modifier.padding(top = 4.dp).preferredWidth(110.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(color = MaterialTheme.colors.onSurface, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        )
        Text(
            text = "15 hrs left",
            modifier = Modifier.padding(top = 2.dp).preferredWidth(110.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(color = MaterialTheme.colors.onSurface, fontSize = 12.sp, fontWeight = FontWeight.Normal)
        )
    }
}

@Composable
fun VotingImage(url: String, modifier: Modifier) {
    Surface(elevation = 16.dp, shape = MaterialTheme.shapes.medium, modifier = modifier) {
        PicassoImage(
            url = url,
            modifier = modifier.aspectRatio(1f).clickable(onClick = {})
        )
    }
}

const val PHOTO_URL =
    "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/nature-quotes-1557340276.jpg?crop=0.666xw:1.00xh;0.168xw,0&resize=640:*"

@Preview(showBackground = true)
@Composable
fun VotingImagePreview() {
    VotingColumn(
        VotingItem(
            "Test Edit Purple",
            "15 hrs left",
            PHOTO_URL
        ), modifier = Modifier.padding(16.dp)
    )
}