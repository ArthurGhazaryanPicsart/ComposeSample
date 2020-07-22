package com.example.composepicsartsample.ui.components

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.composepicsartsample.data.entity.User
import com.example.composepicsartsample.data.entity.WinnerItemModel
import com.example.composepicsartsample.data.entity.getLowUrl
import com.example.composepicsartsample.ui.PicassoImage

@Composable
fun WinnerItem(item: WinnerItemModel, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalGravity = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        VotingImage(url = item.url.getLowUrl(), modifier = Modifier.preferredWidth(120.dp).wrapContentHeight())
        PicassoImage(
            url = item.user.photo.getLowUrl(),
            modifier = Modifier.preferredWidth(40.dp).padding(top = 8.dp).aspectRatio(1f).clip(
                CircleShape
            )
        )
        Text(
            text = item.user.name,
            modifier = Modifier.padding(top = 4.dp),
            style = TextStyle(color = Color.Gray, fontSize = 16.sp, fontWeight = FontWeight.Normal)
        )
        Text(
            text = "${item.place}th place",
            modifier = Modifier.padding(top = 2.dp),
            style = TextStyle(color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WinnerItemPreview() {
    WinnerItem(
        item = WinnerItemModel(
            url = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/nature-quotes-1557340276.jpg?crop=0.666xw:1.00xh;0.168xw,0&resize=640:*",
            user = User(
                name = "omgwinner",
                photo = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/nature-quotes-1557340276.jpg?crop=0.666xw:1.00xh;0.168xw,0&resize=640:*"
                , username = "omgwinner"
            )
        ), modifier = Modifier.padding(16.dp)
    )
}