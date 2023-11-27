package com.example.workflow.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.workflow.ui.navigation.Navigation
import com.example.workflow.ui.theme.WorkFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkFlowTheme {
                // A surface container using the 'background' color from the theme
                Surface() {
                    Navigation()
                }
            }
        }
    }
}