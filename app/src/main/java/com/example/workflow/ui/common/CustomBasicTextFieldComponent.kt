package com.example.workflow.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomBasicTextFieldComponent (
    valueTextField: String,
    onChangeTextField: (String) -> Unit,
    backgroundColor: Color,
    textColor: Color
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    BasicTextField(
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = CircleShape)
            .border(BorderStroke(1.dp, textColor), shape = CircleShape),
        value = valueTextField,
        onValueChange = { onChangeTextField(it) },
        textStyle = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
            color = textColor),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 15.dp)) {
                innerTextField()
            }
        }
    )
}