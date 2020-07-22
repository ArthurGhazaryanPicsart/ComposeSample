package com.example.composepicsartsample.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.animation.LinearOutSlowInEasing
import androidx.animation.TweenBuilder
import androidx.compose.*
import androidx.ui.animation.animatedFloat
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.drawOpacity
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.graphics.Color
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.Stack
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

@Composable
fun PicassoImage(
    url: String,
    placeholder: Color = Color.LightGray,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    var imageAsset by state<Pair<Picasso.LoadedFrom?, ImageAsset?>?> { null }

    onCommit(url) {
        val picasso = Picasso.get()
        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                imageAsset = (from to bitmap?.asImageAsset())
            }
        }

        picasso.load(url).into(target)

        onDispose {
            imageAsset = null
            picasso.cancelRequest(target)
        }
    }
    imageAsset?.second?.let { asset ->
        if (imageAsset?.first == Picasso.LoadedFrom.MEMORY) {
            Image(
                asset = asset,
                modifier = modifier,
                contentScale = contentScale
            )
        } else {
            val alpha = animatedFloat(0f)
            onCommit(asset) {
                alpha.snapTo(0f)
                alpha.animateTo(1f, TweenBuilder<Float>().apply {
                    duration = 300
                    easing = LinearOutSlowInEasing
                })
            }
            Stack {
                Image(
                    asset = asset,
                    modifier = modifier.drawOpacity(alpha.value),
                    contentScale = contentScale
                )
                if (alpha.value < 1) {
                    Box(
                        modifier = modifier.drawOpacity(1 - alpha.value),
                        backgroundColor = placeholder
                    )
                }
            }
        }
    } ?: Box(modifier = modifier, backgroundColor = placeholder)
}

