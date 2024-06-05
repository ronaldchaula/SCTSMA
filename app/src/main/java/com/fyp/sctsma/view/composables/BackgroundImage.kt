package com.fyp.sctsma.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fyp.sctsma.R

@Composable
fun BackgroundImage(){
    Image(
        painter = painterResource(R.drawable.appbackground),
        contentDescription = stringResource(R.string.background_description),
        modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
}

@Composable
@Preview
fun BackgroundImagePreview(){
    BackgroundImage()
}
