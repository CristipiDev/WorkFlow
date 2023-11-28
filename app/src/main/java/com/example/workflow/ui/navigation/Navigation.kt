package com.example.workflow.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.workflow.ui.aboutapp.AboutAppScreen
import com.example.workflow.ui.workflow.WorkflowScreen
import com.example.workflow.ui.workflowmenu.WorkflowMenuScreen

@Composable
fun Navigation(
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.WorkflowMenu.route
    ) {
        composable(
            route = AppRoutes.WorkflowMenu.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(300)
                )
            }) {
            WorkflowMenuScreen(navController = navController,
                darkTheme = darkTheme, onThemeUpdated = onThemeUpdated)
        }
        composable(
            route = AppRoutes.WorkflowInfo.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(300)
                )
            }
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            requireNotNull(id) { "Error msg: It's not going to be null" }

            WorkflowScreen(
                navController = navController,
                workflowId = id
            )
        }

        composable(
            route = AppRoutes.WorkflowAboutApp.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
            ){
            AboutAppScreen(navController)
        }
    }
}