package com.example.workflow.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workflow.ui.navigation.Navigation
import com.example.workflow.ui.theme.WorkFlowTheme
import com.example.workflow.ui.workflowmenu.WorkflowMenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            WorkFlowTheme(darkTheme = darkTheme) {
                Surface() {
                    Navigation(
                        darkTheme
                    ) { darkTheme = !darkTheme }
                }
            }
        }
    }
}