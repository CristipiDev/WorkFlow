package com.example.workflow.domain.model

data class StateModel (
    val stateTitle: String,
    val taskList: ArrayList<TaskModel>
)