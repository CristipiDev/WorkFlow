package com.example.workflow.data.repository

import com.example.workflow.data.database.dao.WorkflowDao
import com.example.workflow.data.database.entity.WorkflowEntity
import com.example.workflow.domain.model.WorkflowMenuModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface WorkflowRepository {
    suspend fun setNewWorkflow(workflowName: String): Int
    suspend fun getAllWorkflows(): Flow<List<WorkflowMenuModel>>
    suspend fun updateWorkflow(workflowData: WorkflowMenuModel)
    suspend fun deleteWorkflowFromId(workflowId: Int)
}

class WorkflowRepositoryImpl @Inject constructor(
    private val workflowDao: WorkflowDao
): WorkflowRepository {
    override suspend fun setNewWorkflow(workflowName: String): Int {
        return workflowDao.insertNewWorkflow(
            WorkflowEntity(workflowName)
        ).toInt()
    }

    override suspend fun getAllWorkflows(): Flow<List<WorkflowMenuModel>> {
        return workflowDao.getAllWorkflows().map {list ->
            list.map {workflowDao ->
                WorkflowMenuModel(
                    workflowId = workflowDao.id.toInt(),
                    workflowTitle =  workflowDao.name
                )
            }
        }
    }

    override suspend fun updateWorkflow(workflowData: WorkflowMenuModel) {
        val workflowEntity = WorkflowEntity(
                                workflowData.workflowTitle,
                                workflowData.workflowId.toLong()
                            )
        workflowDao.updateWorkflow(workflowEntity)
    }

    override suspend fun deleteWorkflowFromId(workflowId: Int) {
        workflowDao.deleteWorkflowFromId(workflowId.toLong())
    }

}