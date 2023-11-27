package com.example.workflow.ui.workflowmenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.workflow.domain.model.WorkflowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkflowMenuViewModel @Inject constructor(): ViewModel() {
    var dataState by mutableStateOf(WorkflowMenuDataState(
        workflowList = setWorkflowList()
    ))

    private fun setWorkflowList(): ArrayList<WorkflowModel> {
        val list: ArrayList<WorkflowModel> = ArrayList()
        list.add(WorkflowModel(
            "Trabajo",
            ArrayList())
        )
        list.add(WorkflowModel(
            "Casa",
            ArrayList())
        )
        list.add(WorkflowModel(
            "Universidad",
            ArrayList())
        )
        list.add(WorkflowModel(
            "Varios",
            ArrayList())
        )
        return list
    }

}