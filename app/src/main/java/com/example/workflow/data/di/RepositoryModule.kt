package com.example.workflow.data.di

import com.example.workflow.data.repository.WorkflowRepository
import com.example.workflow.data.repository.WorkflowRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun workflowRepository(repo: WorkflowRepositoryImpl): WorkflowRepository

}