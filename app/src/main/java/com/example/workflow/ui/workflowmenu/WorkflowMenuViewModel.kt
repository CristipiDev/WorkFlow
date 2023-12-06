package com.example.workflow.ui.workflowmenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.workflow.domain.model.DataProvider

@HiltViewModel
class WorkflowMenuViewModel @Inject constructor(): ViewModel() {
    var dataState by mutableStateOf(WorkflowMenuDataState(
        workflowList = DataProvider.list
    ))
    var uiState by mutableStateOf(WorkflowMenuUiState(
        alignmentSwitch = Alignment.CenterStart
    ))

    fun onSwitchDarkTheme(darkTheme: Boolean) {
        uiState = if (!darkTheme) uiState.copy(alignmentSwitch = Alignment.CenterEnd)
        else uiState.copy(alignmentSwitch = Alignment.CenterStart)
    }

    fun setShowFirstColumn() {
        val show = !uiState.showFirstColumn
        uiState = uiState.copy(
            showFirstColumn = show
        )
    }

    fun setColorsTextButton(isPressed: Boolean, backgroundColor: Color): Pair<Color, Color> {
        val colors: Pair<Color, Color> = Pair(Color(0xFF000000),
            Color(0xFFFFFFFF))
        if(backgroundColor == Color(0xFFFFFFFF)) {
            if (isPressed) {
                return Pair(Color(0xFFFFFFFF), Color(0xFF000000))
            }
        } else {
            if (!isPressed) {
                return Pair(Color(0xFFFFFFFF), Color(0xFF000000))
            }
        }
        return colors
    }

    fun setShowDialog() {
        val isShown = !uiState.showDialog
        uiState = uiState.copy(
            showDialog = isShown,
            textFieldValue = ""
        )
    }

    fun onTextFieldChange(newText: String) {
        uiState = uiState.copy(
            textFieldValue = newText
        )
    }

}