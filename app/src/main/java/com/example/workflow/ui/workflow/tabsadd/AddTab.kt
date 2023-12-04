package com.example.workflow.ui.workflow.tabsadd

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.workflow.ui.common.CustomBasicTextFieldComponent
import com.example.workflow.ui.common.TextButtonComponent
import com.example.workflow.ui.workflow.WorkflowViewModel

@Composable
fun AddTab(
    viewModel: WorkflowViewModel
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val density = LocalDensity.current

    val buttonWidth by animateDpAsState(
        targetValue = if (!viewModel.uiState.expandedTaskBox && !viewModel.uiState.expandedStateBox) 50.dp
        else screenWidth,
        tween(durationMillis = 300), label = ""
    )
    val rotateIcon by animateFloatAsState(
        if (viewModel.uiState.expandNewBox) -45f else 0f,
        tween(durationMillis = 300), label = ""
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        //Add button
        Row(modifier = Modifier
            .size(buttonWidth, 40.dp)
            .background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .onGloballyPositioned {
                val iconHeight = with(density) {
                    it.size.height.toDp()
                }
                viewModel.setIconAddTaskHeight(iconHeight)
            }
            .padding(10.dp, 5.dp, 10.dp, 0.dp)) {
            if (viewModel.uiState.expandedTaskBox || viewModel.uiState.expandedStateBox) {
                Text(
                    text = "New task",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp)
                        .clickable { viewModel.expandNewTaskBox() }
                )
            }
            Box(modifier = Modifier
                .clickable { viewModel.expandNewBox() },
                contentAlignment = Alignment.Center) {
                Icon( imageVector = Icons.Filled.Add,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .rotate(rotateIcon)
                        .size(40.dp))
            }
        }

        Column(modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)) {
            AnimatedVisibility(
                visible = viewModel.uiState.expandedTaskBox,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                //Data new task
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                )
                {
                    Column(modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(20.dp)) {

                        Text(
                            text = "Name:",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.background
                        )
                        CustomBasicTextFieldComponent(
                            "",
                            {},
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.background
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(5.dp)
                        )
                        Text(
                            text = "Description:",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.background
                        )
                        CustomBasicTextFieldComponent(
                            "",
                            {},
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.background,
                            200.dp
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )
                        TextButtonComponent(
                            "Save",
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.background,
                            {  })

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                        )
                        .clickable { viewModel.expandNewStateBox() }
                    ){
                        Text(
                            text = "New State",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = viewModel.uiState.expandedStateBox,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                //Data new task
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background,
                            RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
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

        }
    }
}
