package com.example.workflow.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.workflow.ui.utils.ChangeColorsTextButtonOnPressedUtils

@Composable
fun TextButtonComponent(
    text: String,
    backgroundColor: Color,
    buttonMainColor: Color,
    onClickButton: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(onClick = { onClickButton() },
        modifier = Modifier
            .fillMaxWidth(),
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        contentPadding = PaddingValues(0.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = ChangeColorsTextButtonOnPressedUtils(isPressed, buttonMainColor).second,
                    shape = CircleShape
                )
                .border(
                    BorderStroke(1.dp, ChangeColorsTextButtonOnPressedUtils(isPressed, buttonMainColor).first),
                    shape = CircleShape
                )
                .padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = ChangeColorsTextButtonOnPressedUtils(isPressed, buttonMainColor).first,
                fontWeight = FontWeight.ExtraLight,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

        }
    }
}