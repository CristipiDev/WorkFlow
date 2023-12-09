package com.example.workflow.domain.usecase

import com.example.workflow.data.repository.WorkflowRepository
import javax.inject.Inject

class DeleteWorkflowMenuUseCase @Inject constructor(
    private val workflowRepository: WorkflowRepository
) {

    private var workflowId: Int = -1

    suspend operator fun invoke() {
        workflowRepository.deleteWorkflowFromId(workflowId)
    }

    fun setWorkflowId(id: Int) {
        workflowId = id
    }
}