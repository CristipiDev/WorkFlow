package com.example.workflow.ui.workflow.boxnew

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NewItemBox(
    setIconHeight: (Dp) -> Unit,
   density: Density,
   setShowNewTaskBox: () -> Unit,
   rotateIcon: Float,
   setNewTaskBoxHeight: (Dp) -> Unit
) {
    //Add button
    Row(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 20.dp)
        .onGloballyPositioned {
            val iconHeight = with(density) {
                it.size.height.toDp()
            }
            setIconHeight(iconHeight)
        },
        horizontalArrangement = Arrangement.End) {
        Box(modifier = Modifier
            .size(50.dp, 40.dp)
            .background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .clickable { setShowNewTaskBox() }
            .padding(top = 5.dp),
            contentAlignment = Alignment.Center) {
            Icon( imageVector = Icons.Filled.Add,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .rotate(rotateIcon)
                    .size(40.dp))
        }
    }

    //Data new task
    Column(modifier = Modifier
        .background(
            MaterialTheme.colorScheme.primary,
            RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
        )
        .fillMaxWidth()
        .wrapContentHeight(Alignment.CenterVertically)
        .padding(20.dp)
        .onGloballyPositioned {
            val newTaskBoxHeight = with(density) {
                it.size.height.toDp()
            }
            setNewTaskBoxHeight(newTaskBoxHeight)
        })
    {
        Text(text = "New task",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.background)
    }
}