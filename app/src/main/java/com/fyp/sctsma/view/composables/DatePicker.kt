package com.fyp.sctsma.view.composables

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.util.Calendar

@Composable
fun DatePicker(
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
        val context = LocalContext.current
        val calendar = Calendar.getInstance()

        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val date = "${dayOfMonth}/${month + 1}/$year"
                onDateSelected(date)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()

}

@Preview
@Composable
fun DateOfBirthPickerPreview() {
    var selectedDate by remember { mutableStateOf("") }

    DatePicker(
        selectedDate = selectedDate,
        onDateSelected = { selectedDate = it }
    )
}