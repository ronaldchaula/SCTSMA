package com.fyp.sctsma.view.composables.commonComponents

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.fyp.sctsma.R

@Composable
fun UrlImageLoader(imageUrl: String, context : Context = LocalContext.current, defaultImage : Int = R.drawable.user_avatar, modifier: Modifier) {
    var imageLoaded by remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        onError = {
            imageLoaded = false
        },
        onSuccess = {
            imageLoaded = true
        }
    )

    if (imageLoaded) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(painter)
                .crossfade(true)
                .build(),
            contentDescription = "Image from URL",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    } else {
        Image(
            painter = painterResource(id = defaultImage),
            contentDescription = "Default Image",
            modifier = modifier
                ,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun UrlImageLoaderPreview() {
    val imageUrl = "https://images.unsplash.com/photo-1531901599143-df5010ab9438?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    UrlImageLoader(
        imageUrl = imageUrl,
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
        )
}