package com.example.workflow.ui.workflow.tabsadd

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.with
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.workflow.ui.common.CustomBasicTextFieldComponent
import com.example.workflow.ui.common.TextButtonComponent
import com.example.workflow.ui.workflow.WorkflowViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AddTab(
    viewModel: WorkflowViewModel
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val density = LocalDensity.current

    val height by animateDpAsState(
        targetValue = if (viewModel.uiState.expandedTaskBox && !viewModel.uiState.expandedStateBox)
            500.dp
        else if (viewModel.uiState.expandedTaskBox && viewModel.uiState.expandedStateBox) 400.dp
        else viewModel.uiState.iconHeight,
        tween(durationMillis = 300, delayMillis = 300), label = ""
    )

    val heightState by animateDpAsState(
        targetValue = if (viewModel.uiState.expandedStateBox) 400.dp
        else 0.dp,
        tween(durationMillis = 300), label = ""
    )

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

        Column(modifier = Modifier.fillMaxWidth()) {
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
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(20.dp)
                )
                {
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
                        { viewModel.expandNewStateBox() })

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
                        .background(MaterialTheme.colorScheme.background)
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

@Composable
fun ExpandableBottomSheet() {
    var expanded by remember { mutableStateOf(false) }

    val height by animateDpAsState(
        targetValue = if (expanded) 400.dp else 56.dp,
        animationSpec = spring(), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Color.White)
    ) {
        Button(
            onClick = { expanded = !expanded },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(if (expanded) "Collapse Bottom Sheet" else "Expand Bottom Sheet")
        }

        if (expanded) {
            // Content for the expanded state
            Text("Bottom Sheet Expanded Content", modifier = Modifier.padding(16.dp))
        }
    }
}