package com.example.workflow.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workflow")
data class WorkflowEntity (
    @PrimaryKey val id: Long,
    val name: String
)