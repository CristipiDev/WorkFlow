package com.example.workflow.domain.usecase

import com.example.workflow.data.repository.WorkflowRepository
import com.example.workflow.domain.model.WorkflowMenuModel
import javax.inject.Inject

class UpdateWorkflowMenuUseCase @Inject constructor(
    private val workflowRepository: WorkflowRepository
) {
    private var workflowData: WorkflowMenuModel = WorkflowMenuModel()
    suspend operator fun invoke() {
        workflowRepository.updateWorkflow(workflowData)
    }

    fun setWorkflowData(data: WorkflowMenuModel) {
        workflowData = data
    }
}