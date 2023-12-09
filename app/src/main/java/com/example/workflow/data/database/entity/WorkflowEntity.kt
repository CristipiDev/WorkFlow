package com.example.workflow.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workflow")
data class WorkflowEntity (
    val name: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)