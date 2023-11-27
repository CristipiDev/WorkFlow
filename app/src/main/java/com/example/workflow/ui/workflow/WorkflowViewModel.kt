package com.example.workflow.ui.workflow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.workflow.domain.model.StateModel
import com.example.workflow.domain.model.TaskModel
import com.example.workflow.domain.model.WorkflowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkflowViewModel @Inject constructor(): ViewModel() {
    var dataState by mutableStateOf(WorkflowDataState(
            workflowInfo = setWorkflowInfo())
    )
    var uiState by mutableStateOf(WorkflowUiState(
        complitedPercentage = getPercentageComplited()
    ))

    fun getIsCompleted(state: StateModel, stateList: ArrayList<StateModel>){
        uiState = if (state == stateList.last()) {
            uiState.copy(
                isClompleted = true
            )
        } else {
            uiState.copy(
                isClompleted = false
            )
        }
    }

    fun getPercentageComplited(): Float {
        val lastSize = dataState.workflowInfo.stateList.last().taskList.size
        var allSize = 0.0f
        dataState.workflowInfo.stateList.forEach {
            allSize += it.taskList.size
        }
        return lastSize/allSize
    }
    private fun setWorkflowInfo(): WorkflowModel {
        val taskList1: ArrayList<TaskModel> = ArrayList()
        taskList1.add(TaskModel(
            "Navigation 1",
            "Navigation menu to info",
            true, false
        ))
        taskList1.add(TaskModel(
            "Desing About",
            "desing button About int menu",
            false, false
        ))
        taskList1.add(TaskModel(
            "Desing 'Add workflow'",
            "Desing button 'Add workflow' in menu",
            false, false
        ))
        val taskList2: ArrayList<TaskModel> = ArrayList()
        taskList2.add(TaskModel(
            "set data",
            "Add the data into the view menu",
            true, false
        ))

        val taskList3: ArrayList<TaskModel> = ArrayList()
        taskList3.add(TaskModel(
            "Desing view V.1",
            "Desing menu and info screens V.1",
            false, true
        ))

        val stateList: ArrayList<StateModel> = ArrayList()
        stateList.add(
            StateModel(
                "New",
                taskList1
            )
        )
        stateList.add(
            StateModel(
                "In progress",
                taskList2
            )
        )
        stateList.add(
            StateModel(
                "Complited",
                taskList3
            )
        )
        return WorkflowModel(
            "Trabajo",
            stateList
        )
    }
}