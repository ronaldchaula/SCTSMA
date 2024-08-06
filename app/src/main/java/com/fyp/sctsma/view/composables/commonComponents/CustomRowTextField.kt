package com.fyp.sctsma.view.composables.commonComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
fun CustomRowTextField(
    title: String,
    content: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .wrapContentHeight()

    ) {
        Text(
            text = "$title :",
            modifier = Modifier
                .fillMaxWidth(.5f)
                .padding(
                    horizontal = 4.dp,
                    vertical = 2.dp
                ),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF2F4858),
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = content,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = 4.dp,
                    vertical = 8.dp),
            style = TextStyle(
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomRowTextFieldPreview() {
    CustomRowTextField(
        title = "Title",
        content = "Content"
    )

}