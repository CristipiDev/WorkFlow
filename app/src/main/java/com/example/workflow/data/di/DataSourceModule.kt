package com.example.workflow.data.di

import android.content.Context
import androidx.room.Room
import com.example.workflow.data.database.WorkflowLocalDataSource
import com.example.workflow.data.database.dao.StateDao
import com.example.workflow.data.database.dao.WorkflowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    //Room
    @Singleton
    @Provides
    fun workflowLocalDataSource(@ApplicationContext context: Context): WorkflowLocalDataSource {
        return Room.databaseBuilder(context, WorkflowLocalDataSource::class.java, "workflow_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun workflowDao(db: WorkflowLocalDataSource): WorkflowDao = db.workflowDao()

    @Singleton
    @Provides
    fun stateDao(db: WorkflowLocalDataSource): StateDao = db.stateDao()
}