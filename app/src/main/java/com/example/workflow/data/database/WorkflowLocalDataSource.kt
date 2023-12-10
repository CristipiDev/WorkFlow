package com.example.workflow.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.workflow.data.database.dao.StateDao
import com.example.workflow.data.database.dao.WorkflowDao
import com.example.workflow.data.database.entity.StateEntity
import com.example.workflow.data.database.entity.WorkflowEntity
import com.example.workflow.data.database.entity.WorkflowStateCrossResEntity

@Database(entities = [WorkflowEntity::class, StateEntity::class,
                        WorkflowStateCrossResEntity::class], version = 1)
abstract class WorkflowLocalDataSource: RoomDatabase() {

    abstract fun workflowDao(): WorkflowDao

    abstract fun stateDao(): StateDao

}