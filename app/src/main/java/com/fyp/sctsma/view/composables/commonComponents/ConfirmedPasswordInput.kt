package com.fyp.sctsma.view.composables.commonComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmedPasswordInput(
    confirmPassword: String,
    onConfirmedPasswordChange: (String) -> Unit,
    confirmPasswordBorderColor: Color,
    confirmPasswordVisibility: Boolean,
    onConfirmPasswordVisibilityChange: (Boolean) -> Unit
) {

    Text(
        text = "confirm Password",
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858)
        ),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(bottom = 2.5.dp, top = 2.5.dp)
    )
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick =  {onConfirmPasswordVisibilityChange(!confirmPasswordVisibility) }
            ) {
                Icon(
                    imageVector = if (confirmPasswordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (confirmPasswordVisibility) "Hide password" else "Show password"
                )
            }
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858)
        ),
        value = confirmPassword,
        onValueChange = onConfirmedPasswordChange,
        placeholder = {
            Text(
                text = "xxxxxxxxxx",
                fontSize = 14.sp
            )
        },
        //styling

        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(50.dp)
            .border(
                width = .1.dp,
                color = confirmPasswordBorderColor,
                shape = RoundedCornerShape(15.dp)
            )
            .background(Color(0xFFFAFAFA))
    )
}