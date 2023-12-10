package com.example.workflow.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "state")
data class StateEntity (
    val name: String,
    @PrimaryKey(autoGenerate = true) var stateId: Long = 0
)