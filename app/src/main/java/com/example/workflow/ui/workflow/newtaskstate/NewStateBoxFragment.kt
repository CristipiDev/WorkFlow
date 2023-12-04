package com.example.workflow.ui.workflow.newtaskstate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.workflow.ui.common.CustomBasicTextFieldComponent
import com.example.workflow.ui.common.TextButtonComponent

@Composable
fun NewStateBoxFragment() {
    //Data new task
    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(20.dp)
    )
    {
        Text(
            text = "New State",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        Text(
            text = "Name:",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        CustomBasicTextFieldComponent(
            "",
            {},
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        TextButtonComponent(
            "Save",
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary,
            {})

    }
}