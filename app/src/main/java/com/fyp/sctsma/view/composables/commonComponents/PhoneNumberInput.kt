package com.fyp.sctsma.view.composables.commonComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PhoneNumberInput(
    phoneNumber: String,
    onContactPhoneChange: (String) -> Unit,
    phoneNumberBorderColor: Color
) {
    Text(
        text = "Phone Number",
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858)
        ),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(bottom = 2.5.dp)
    )

    //phone input
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        value = phoneNumber,
        onValueChange = onContactPhoneChange,
        placeholder = {
            Text(
                text = "255-XXX-XXXXXXX",
                fontSize = 14.sp
            )
        },
        //styling

        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(50.dp)
            .border(
                width = 1.dp,
                color = phoneNumberBorderColor,
                shape = RoundedCornerShape(15.dp)
            )
            .background(Color(0xFFFAFAFA))
    )
}