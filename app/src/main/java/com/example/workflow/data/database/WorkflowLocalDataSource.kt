package com.example.workflow.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.workflow.data.database.dao.WorkflowDao
import com.example.workflow.data.database.entity.WorkflowEntity

@Database(entities = [WorkflowEntity::class], version = 1)
abstract class WorkflowLocalDataSource: RoomDatabase() {

    abstract fun workflowDao(): WorkflowDao

}