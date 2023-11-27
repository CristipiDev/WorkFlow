package com.example.workflow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.workflow.ui.workflow.WorkflowScreen
import com.example.workflow.ui.workflowmenu.WorkflowMenuScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoutes.WorkflowMenu.route
    ) {
        composable(route = AppRoutes.WorkflowMenu.route) {
            WorkflowMenuScreen(navController = navController)
        }
        composable(route = AppRoutes.WorkflowInfo.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            requireNotNull(id) { "Error msg: It's not going to be null" }

            WorkflowScreen(
                navController = navController,
                workflowId = id
            )
        }
    }
}