package com.example.workflow.ui.workflowmenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.workflow.domain.model.DataProvider

@HiltViewModel
class WorkflowMenuViewModel @Inject constructor(): ViewModel() {
    var dataState by mutableStateOf(WorkflowMenuDataState(
        workflowList = DataProvider.list
    ))

    fun onSwitchLight() {

    }
}