package com.example.composepicsartsample.ui.components

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TextButton
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import androidx.ui.unit.sp

@Composable
fun Title(
    title: String,
    subtitle: String? = null,
    seeAll: Boolean,
    modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
    seeAllOnClick: () -> Unit = {},
    textSize: TextUnit = 16.sp,
    subTitleTextSize: TextUnit = 14.sp
) {
    Row(
        modifier = modifier,
        verticalGravity = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.body1.copy(
                    fontSize = textSize,
                    fontWeight = FontWeight.Medium
                )
            )
            subtitle?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = subTitleTextSize,
                        color = Color(0xFF888888)
                    )
                )
            }
        }
        if (seeAll) {
            TextButton(
                contentColor = Color.LightGray,
                text = {
                    Text(
                        "See All",
                        color = MaterialTheme.colors.secondary
                    )
                },
                onClick = seeAllOnClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    Title(title = "Start Voting", seeAll = true)
}