package components.commonComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fyp.sctsma.R

@Composable
fun InputField() {
    OutlinedTextField(
        value ="" ,
        onValueChange ={},
        placeholder = {
            Text(text = stringResource(R.string.phone_input_label))
        },
        //styling

        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(55.dp)
            .border(
                width = .1.dp,
                color = Color(0xFF2F4858),
                shape = RoundedCornerShape(15.dp)
            )
            .background(Color(0xFFFAFAFA))
    )
}