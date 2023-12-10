package com.example.workflow.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.workflow.data.database.entity.WorkflowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkflowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewWorkflow(workflow: WorkflowEntity): Long

    @Query("SELECT * FROM workflow ORDER BY workflowId ASC")
    fun getAllWorkflows(): Flow<List<WorkflowEntity>>

    @Query("SELECT * FROM workflow WHERE workflowId = :workflowId")
    fun getWorkflowFromId(workflowId: Long): WorkflowEntity

    @Update
    fun updateWorkflow(workflowEntity: WorkflowEntity)

    @Query("DELETE FROM workflow WHERE workflowId = :workflowId")
    fun deleteWorkflowFromId(workflowId: Long)
}