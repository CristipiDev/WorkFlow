package com.example.workflow.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.workflow.data.database.entity.StateEntity
import com.example.workflow.data.database.entity.WorkflowStateCrossResEntity
import com.example.workflow.data.database.entity.WorkflowWithStatesEntity

@Dao
interface StateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewState(state: StateEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStateIntoWorkflow(join: WorkflowStateCrossResEntity)

    @Query("SELECT * FROM workflow WHERE workflowId = :id")
    fun getStatesFromWorkflowId(id: Long): WorkflowWithStatesEntity
}