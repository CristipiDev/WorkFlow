package com.example.workflow.ui.workflowmenu

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.workflow.domain.model.WorkflowMenuModel
import com.example.workflow.domain.usecase.DeleteWorkflowMenuUseCase
import com.example.workflow.domain.usecase.GetWorkflowMenuUseCase
import com.example.workflow.domain.usecase.SetWorkflowMenuUseCase
import com.example.workflow.domain.usecase.UpdateWorkflowMenuUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class WorkflowMenuViewModel @Inject constructor(
    private val getWorkflowMenuUseCase: GetWorkflowMenuUseCase,
    private val setWorkflowMenuUseCase: SetWorkflowMenuUseCase,
    private val updateWorkflowMenuUseCase: UpdateWorkflowMenuUseCase,
    private val deleteWorkflowMenuUseCase: DeleteWorkflowMenuUseCase
): ViewModel() {
    var dataState: MutableState<List<WorkflowMenuModel>> = mutableStateOf(emptyList())
    var uiState by mutableStateOf(WorkflowMenuUiState(
        alignmentSwitch = Alignment.CenterStart
    ))

    init {
        viewModelScope.launch {
            getWorkflowMenuUseCase.invoke()
                .onEach {
                    dataState.value = it
                } .catch { Log.d("MyError", "Error_getWorkflowList_in_menu: " + it.message.orEmpty()) }
                .launchIn(viewModelScope)
        }
    }

    fun setWorkflow() {
        viewModelScope.launch(Dispatchers.IO) {
            if (uiState.workflowData.workflowId == -1) {
                setWorkflowMenuUseCase.setWorkflowName(uiState.workflowData.workflowTitle)
                setWorkflowMenuUseCase.invoke()
            }
            else {
                updateWorkflowMenuUseCase.setWorkflowData(uiState.workflowData)
                updateWorkflowMenuUseCase.invoke()
            }
        }
        uiState = uiState.copy(
            showDialog = false
        )
    }

    fun deleteWorkflow(workflowId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteWorkflowMenuUseCase.setWorkflowId(workflowId)
            deleteWorkflowMenuUseCase.invoke()
        }
    }

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

    fun setShowDialog(data: WorkflowMenuModel? = null) {
        val isShown = !uiState.showDialog
        val workflowData = data?.let { data } ?: kotlin.run { WorkflowMenuModel() }
        uiState = uiState.copy(
            showDialog = isShown,
            workflowData = workflowData
        )
    }

    fun onTextFieldChange(newText: String) {
        val workflowData = WorkflowMenuModel(
            uiState.workflowData.workflowId,
            newText)
        uiState = uiState.copy(
            workflowData = workflowData
        )
    }

}