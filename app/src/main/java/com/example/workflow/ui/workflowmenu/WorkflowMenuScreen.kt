package com.example.workflow.ui.workflowmenu

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workflow.domain.model.WorkflowModel
import com.example.workflow.ui.aboutapp.WorkflowAboutAppScreen
import com.example.workflow.ui.common.CustomBasicTextFieldComponent
import com.example.workflow.ui.common.SwitchAndIconComponent
import com.example.workflow.ui.common.TextButtonComponent
import com.example.workflow.ui.navigation.AppRoutes

@Composable
fun WorkflowMenuScreen(
    viewModel: WorkflowMenuViewModel = hiltViewModel(),
    navController: NavController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val firstColumnOffset by animateDpAsState(
        if (viewModel.uiState.showFirstColumn) 0.dp else (-screenWidth),
        tween(durationMillis = 300), label = ""
    )

    val secondColumnOffset by animateDpAsState(
        if (viewModel.uiState.showFirstColumn) screenWidth else 0.dp,
        tween(durationMillis = 300), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = firstColumnOffset, y = 0.dp)
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(bottom = 20.dp)
        ) {

            HeaderRow(viewModel::setShowFirstColumn)

            Column(
                modifier = Modifier
                    .weight(1f)) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(viewModel.dataState.workflowList) { workflow ->
                        MenuItem(workflow, navController, viewModel)
                    }
                }
                Box(modifier = Modifier
                    .padding(30.dp, 40.dp, 30.dp, 0.dp)) {
                    TextButtonComponent(
                        "+ Add Workflow",
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primary,
                        viewModel::setShowDialog)
                }

            }

            SwitchAndIconComponent(viewModel.uiState.alignmentSwitch,
                    darkTheme, onThemeUpdated, viewModel::onSwitchDarkTheme)
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .offset(x = secondColumnOffset, y = 0.dp)
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(20.dp, 0.dp, 20.dp, 20.dp)) {
            WorkflowAboutAppScreen(viewModel::setShowFirstColumn)
        }
        NewWorkflowDialog(
            viewModel,
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary)
    }

}

@Composable
private fun HeaderRow(
    onClickAbout: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .padding(horizontal = 10.dp)) {
        Text(text = "WorkFlow",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.background)
        /*TODO poner el icono de la app*/

        Text(text = "i",
            modifier = Modifier
                .size(50.dp)
                .clickable { onClickAbout() },
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.background,
            textAlign = TextAlign.Center)
    }
}

@Composable
private fun MenuItem(
    workflowData: WorkflowModel,
    navController: NavController,
    viewModel: WorkflowMenuViewModel
){
    var showSubMenu by remember {
        mutableStateOf(false)
    }

    val offsetX by animateDpAsState(
        if (showSubMenu) (-140).dp else 0.dp,
        tween(durationMillis = 300), label = ""
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.CenterEnd) {

        Row(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            Box(modifier = Modifier.size(70.dp, 50.dp)
                .clickable { viewModel.setShowDialog(workflowData) },
                contentAlignment = Alignment.Center) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.primary)
            }
            Box(modifier = Modifier.size(70.dp, 50.dp)
                .clickable { Log.d("submenu", "Delete: ${workflowData.workflowTitle}") },
                contentAlignment = Alignment.Center) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.primary)
            }
        }

        Box(modifier = Modifier
            .offset(offsetX, 0.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .clickable { navController.navigate(AppRoutes.WorkflowInfo.route + "/${workflowData.workflowId}") }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    showSubMenu = dragAmount < 0
                    change.consume()
                }
            }) {
            Text(
                text = workflowData.workflowTitle,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(30.dp, 10.dp, 30.dp, 10.dp)
                    .fillMaxWidth()
            )
        }


    }
}



@Composable
fun NewWorkflowDialog(
    viewModel: WorkflowMenuViewModel,
    backgroundColor: Color,
    buttonMainColor: Color
) {
    if (viewModel.uiState.showDialog) {
        Dialog(onDismissRequest = { viewModel.setShowDialog() }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close pop-up for add new workflow",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { viewModel.setShowDialog() }
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp))
                    CustomBasicTextFieldComponent(viewModel.uiState.workflowData.workflowTitle,
                        viewModel::onTextFieldChange,
                        MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp))
                    TextButtonComponent("Save", backgroundColor, buttonMainColor, {})
                }

            }
        }
    }
}
