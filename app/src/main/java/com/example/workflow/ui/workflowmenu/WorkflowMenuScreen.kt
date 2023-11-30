package com.example.workflow.ui.workflowmenu

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workflow.R
import com.example.workflow.ui.navigation.AppRoutes
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.example.workflow.ui.aboutapp.WorkflowAboutAppScreen
import com.example.workflow.ui.common.CustomBasicTextFieldComponent
import com.example.workflow.ui.common.SwitchAndIconComponent
import com.example.workflow.ui.common.SwitchComponent
import com.example.workflow.ui.common.TextButtonComponent

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
                LazyColumn() {
                    items(viewModel.dataState.workflowList) { workflow ->
                        MenuItem(workflow.workflowId, workflow.workflowTitle, navController)
                    }
                }
                Box(modifier = Modifier
                    .padding(30.dp, 40.dp, 30.dp, 0.dp)) {
                    TextButtonComponent(
                        "+ Add Workflow",
                        viewModel::setColorsTextButton,
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
            viewModel.uiState.showDialog,
            viewModel::setShowDialog,
            viewModel.uiState.textFieldValue,
            viewModel::onTextFieldChange,
            viewModel::setColorsTextButton,
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary)
    }

}

@Composable
private fun HeaderRow(
    onClickAbout: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)) {
            Text(text = "ICONO")
            /*TODO poner el icono de la app*/
        }
        Text(text = "i",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(50.dp)
                .clickable { onClickAbout() },
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.background,
            textAlign = TextAlign.Center)
    }
}


@Composable
private fun MenuItem(
    id: Int,
    text: String,
    navController: NavController
){
    Text(text = text,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(30.dp, 20.dp, 30.dp, 0.dp)
            .fillMaxWidth()
            .clickable { navController.navigate(AppRoutes.WorkflowInfo.route + "/$id") })
}



@Composable
fun NewWorkflowDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    textFieldValue: String,
    onChangeTextField: (String) -> Unit,
    onPressedButtonListener: (Boolean, Color) -> Pair<Color, Color>,
    backgroundColor: Color,
    buttonMainColor: Color
) {
    if (showDialog) {
        Dialog(onDismissRequest = { onDismissRequest() }) {
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
                        modifier = Modifier.clickable { onDismissRequest() }
                    )
                    Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
                    CustomBasicTextFieldComponent(textFieldValue, onChangeTextField)
                    Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
                    TextButtonComponent("Save", onPressedButtonListener, backgroundColor, buttonMainColor, {})
                }

            }
        }
    }
}

@Preview
@Composable
fun Preview() {
}