package com.example.workflow.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class WorkflowWithStatesEntity (
    @Embedded val workflow: WorkflowEntity,
    @Relation(
        parentColumn = "workflowId",
        entityColumn = "stateId",
        associateBy = Junction(WorkflowStateCrossResEntity::class)
    )
    val state: List<StateEntity>
)