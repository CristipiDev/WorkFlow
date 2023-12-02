package com.example.workflow.ui.workflow.tabsadd

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workflow.ui.common.CustomBasicTextFieldComponent
import com.example.workflow.ui.workflow.WorkflowViewModel

@Composable
fun AddTab(
    viewModel: WorkflowViewModel,
    density: Density,
    rotateIcon: Float
) {

    //Add button
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .onGloballyPositioned {
            val iconHeight = with(density) {
                it.size.height.toDp()
            }
            viewModel.setIconAddTaskHeight(iconHeight)
        },
        horizontalArrangement = Arrangement.End) {
        Box(modifier = Modifier
            .size(50.dp, 40.dp)
            .background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .clickable { viewModel.setShowNewTaskBox() }
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
            viewModel.setNewTaskBoxHeight(newTaskBoxHeight)
        })
    {
        Text(text = "New task",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.background)
        CustomBasicTextFieldComponent("", {}, MaterialTheme.colorScheme.primary
        , MaterialTheme.colorScheme.background)
        Text(text = "New task",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.background)
    }

    //Data new state
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(
            MaterialTheme.colorScheme.primary
        )) {
        Column(modifier = Modifier
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(20.dp)
            .onGloballyPositioned {

            })
        {
            Text(text = "New state",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.onGloballyPositioned {
                    val titleNewStateHeight = with(density) {
                        it.size.height.toDp()
                    }
                    viewModel.setTitleNewStateHeight(titleNewStateHeight)
                })
        }
    }
}