package com.example.workflow.ui.navigation

sealed class AppRoutes(val route: String) {
    object WorkflowMenu: AppRoutes(route = "menu")
    object WorkflowInfo: AppRoutes(route = "info_screen")
}