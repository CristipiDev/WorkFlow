package com.example.workflow.domain.model

data class TaskModel (
    val taskTitle: String,
    val tastDescription: String,
    val taskIsPriority: Boolean,
    val isCompleted: Boolean
)