package com.example.workflow.ui.workflow.tabsadd

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.workflow.ui.common.CustomBasicTextFieldComponent
import com.example.workflow.ui.common.TextButtonComponent
import com.example.workflow.ui.workflow.WorkflowViewModel

@Composable
fun AddTab(
    viewModel: WorkflowViewModel,
    density: Density,
    rotateIcon: Float
) {

    val newStateBoxOffset by animateDpAsState(
        if (viewModel.uiState.showNewStateBox)
            viewModel.uiState.newStateBoxHeight
        else 0.dp,
        tween(durationMillis = 300), label = ""
    )

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
            .clickable { viewModel.setShowNewTabsBox() }
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
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.onGloballyPositioned {
                val titleNewTaskHeight = with(density) {
                    it.size.height.toDp()
                }
                viewModel.setTitleNewTaskHeight(titleNewTaskHeight)
            }
                .clickable { viewModel.setShowNewTabsBox() })
        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        Text(text = "Name:",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background)
        CustomBasicTextFieldComponent("", {}, MaterialTheme.colorScheme.primary
        , MaterialTheme.colorScheme.background)

        Spacer(modifier = Modifier.fillMaxWidth().height(5.dp))
        Text(text = "Description:",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background)
        CustomBasicTextFieldComponent("", {}, MaterialTheme.colorScheme.primary
            , MaterialTheme.colorScheme.background, 200.dp)
        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
        TextButtonComponent("Save", MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.background, {})

    }

    //Data new state
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(
            MaterialTheme.colorScheme.primary
        )
        .clickable {viewModel.setShowNewStateBox() }
        .onGloballyPositioned {
            val newStateBoxHeight = with(density) {
                it.size.height.toDp()
            }
            viewModel.setNewStateBoxHeight(newStateBoxHeight)
        }
        /*.offset(0.dp, -newStateBoxOffset)*/) {
        Column(modifier = Modifier
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(20.dp))
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

            Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
            Text(text = "Name:",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary)
            CustomBasicTextFieldComponent("", {}, MaterialTheme.colorScheme.background
                , MaterialTheme.colorScheme.primary)

            Spacer(modifier = Modifier.fillMaxWidth().height(5.dp))
            TextButtonComponent("Save", MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.primary, {})
        }
    }
}