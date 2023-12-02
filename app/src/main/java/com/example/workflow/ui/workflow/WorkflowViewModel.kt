package com.example.workflow.ui.workflow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.workflow.domain.model.DataProvider.Companion.list
import com.example.workflow.domain.model.StateModel
import com.example.workflow.domain.model.TaskModel
import com.example.workflow.domain.model.WorkflowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkflowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val id: Int? = savedStateHandle["id"]

    var dataState by mutableStateOf(WorkflowDataState(
            workflowInfo = getWorkflowInfo(id))
    )
    var uiState by mutableStateOf(WorkflowUiState(
        complitedPercentage = getPercentageComplited()
    ))


    private fun getPercentageComplited(): Float {
        val lastSize = dataState.workflowInfo.stateList.last().taskList.size
        var allSize = 0.0f
        dataState.workflowInfo.stateList.forEach {
            allSize += it.taskList.size
        }
        return lastSize/allSize
    }

    private fun getWorkflowInfo(id: Int?):  WorkflowModel {
        list.forEach {workflow ->
            if (workflow.workflowId == id) return workflow
        }
        return WorkflowModel()
    }

    /** Show new task tab or new State **/
    fun setShowNewTaskBox() {
        val shown = !uiState.showNewTaskBox
        uiState = uiState.copy(
            showNewTaskBox = shown
        )
    }

    /** Add Button height **/
    fun setIconAddTaskHeight(height: Dp) {
        uiState = uiState.copy(
            iconHeight = height
        )
    }

    fun setTitleNewStateHeight(height: Dp) {
        uiState = uiState.copy(
            titleNewStateHeight = height
        )
    }

    fun setNewTaskBoxHeight(height: Dp) {
        uiState = uiState.copy(
            newTaskBoxHeight = height
        )
    }



}