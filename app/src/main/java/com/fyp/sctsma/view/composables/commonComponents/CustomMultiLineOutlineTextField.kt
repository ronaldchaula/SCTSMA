package com.fyp.sctsma.view.composables.commonComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.sctsma.R

@Composable
fun CustomMultiLineOutlinedTextField(
    title: String?,
    value: String?,
    onValueChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .wrapContentHeight()

    ) {
        Text(
            text = title!!,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 4.dp,
                    vertical = 2.dp
                ),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = value?:"",
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = "Physical Address Required",
                    fontSize = 16.sp
                )
            },

            //styling
            maxLines = Int.MAX_VALUE,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            ),

            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFFAFAFA))
                .padding(
                    horizontal = 4.dp,
                    vertical = 8.dp
                ),

            colors = OutlinedTextFieldDefaults.colors(
                //unfocused 0xFF2F4858
                //focused 0xFF6FC07A
                focusedBorderColor = Color(0xFF6FC07A),
                unfocusedBorderColor = Color(0xFF2F4858),
                disabledBorderColor = Color(0x33000000)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomMultiLineOutlinedTextFieldPreview() {
    CustomMultiLineOutlinedTextField(
        title = "Title",
        value = "Content"
    ) {}
//    CustomTextField(
//        title = "Title",
//        content = "Content"
//    )
}

