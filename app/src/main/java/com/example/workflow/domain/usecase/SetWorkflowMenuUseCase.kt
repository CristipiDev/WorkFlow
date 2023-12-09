package com.example.workflow.domain.usecase

import com.example.workflow.data.repository.WorkflowRepository
import javax.inject.Inject

class SetWorkflowMenuUseCase @Inject constructor(
    private val workflowRepository: WorkflowRepository
) {
    private var workflowName: String = ""
    suspend operator fun invoke(): Int {
        return workflowRepository.setNewWorkflow(workflowName)
    }

    fun setWorkflowName(name: String) {
        workflowName = name
    }
}