package com.example.workflow.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["workflowId", "stateId"],
    foreignKeys = [
        ForeignKey(
            entity = WorkflowEntity::class,
            parentColumns = ["workflowId"],
            childColumns = ["workflowId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StateEntity::class,
            parentColumns = ["stateId"],
            childColumns = ["stateId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class WorkflowStateCrossResEntity (
    val workflowId: Long,
    val stateId: Long
)