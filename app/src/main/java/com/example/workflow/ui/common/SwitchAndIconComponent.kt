package com.example.workflow.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.workflow.R


@Composable
fun SwitchAndIconComponent(
    alignmentSwitch: Alignment?,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    onSwitchDarkTheme: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.lightbulb_fill),
            contentDescription = "Light mode",
            modifier = Modifier
                .padding(end = 10.dp)
                .size(35.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background)
        )
        alignmentSwitch?.let {
            SwitchComponent(
                darkTheme, onThemeUpdated, onSwitchDarkTheme,
                it
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(35.dp)
        )
    }
}
@Composable
fun SwitchComponent(
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    changeSwitch: (Boolean) -> Unit,
    alignment: Alignment
) {
    Box(modifier = Modifier
        .size(width = 60.dp, height = 30.dp)
        .background(
            color = MaterialTheme.colorScheme.background,
            shape = CircleShape
        )
        .clickable {
            onThemeUpdated()
            changeSwitch(darkTheme)
        },
        contentAlignment = alignment){
        Box(modifier = Modifier
            .size(30.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .border(
                BorderStroke(2.dp, MaterialTheme.colorScheme.background),
                shape = CircleShape
            ))
    }
}