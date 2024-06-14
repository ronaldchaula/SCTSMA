package com.fyp.sctsma.view.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AccountType(
    options: List<String>,
    selectedOption: MutableState<String>
    ,onOptionSelectedChange: (String) -> Unit
){

    Column {
        options.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        selectedOption.value = text
                        onOptionSelectedChange(text)
                               },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(Color(0xFF6FC07A)),
                    selected = (text == selectedOption.value),
                    onClick = null,
                )
                Text(text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858)
                    ),
                    modifier = Modifier.padding(start = 16.dp))
            }
        }
    }
}