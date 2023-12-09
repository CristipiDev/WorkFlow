package com.example.workflow.domain.usecase

import com.example.workflow.data.repository.WorkflowRepository
import com.example.workflow.domain.model.WorkflowMenuModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWorkflowMenuUseCase @Inject constructor(
    private val workflowRepository: WorkflowRepository
) {
    suspend operator fun invoke(): Flow<List<WorkflowMenuModel>> {
        return workflowRepository.getAllWorkflows()
    }
}