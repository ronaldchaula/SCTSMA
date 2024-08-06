package com.fyp.sctsma.view.composables.commonComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.sctsma.R

@Composable
fun CallToAction(
    modifier: Modifier,
    onCallToActionClick: () -> Unit,
    actionText: String = "Pay",
    callToActionButtonColor: Color = Color(0xFF6FC07A)
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Button(
            onClick = onCallToActionClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = callToActionButtonColor
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(bottom = 2.5.dp)

        ) {
            Text(
                text = actionText,
                modifier = modifier
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterVertically),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFFFAFAFA),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }

}

@Preview
@Composable
fun CallToActionPreview(){
    CallToAction(Modifier,
    { },
    actionText = "Pay",
    Color(0xFF6FC07A)
        )
}