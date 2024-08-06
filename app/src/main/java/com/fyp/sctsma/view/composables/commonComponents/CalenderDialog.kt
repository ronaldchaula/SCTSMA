package com.fyp.sctsma.view.composables.commonComponents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate

@Composable
fun DOBPicker() {
var pickedDate by remember {
    mutableStateOf(LocalDate.now())
}
    val formattedDate by remember {
        derivedStateOf {
            "${pickedDate.dayOfMonth}/${pickedDate.month}/${pickedDate.year}"
        }
    }
}

@Preview
@Composable
fun DOBPickerPreview() {
    DOBPicker()
}