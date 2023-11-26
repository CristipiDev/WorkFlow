package com.example.workflow.ui.workflow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkflowScreen() {

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("WorkFlow") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ))

        }
        
    ) {innerPadding->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.background)) {

            stateColumn()
            taskCard()

        }
        

    }
}

@Composable
fun stateColumn() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
    ) {
        Text(text = "Hola mundo", style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(5.dp)
        ) {
            Row (modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)) {
                var selected by remember { mutableStateOf(false) }

                RadioButton(selected = selected, onClick = { selected = !selected },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.background,
                        unselectedColor = MaterialTheme.colorScheme.background
                    )
                )
                Column {
                    Text(text = "Task name",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.background)
                    Text(text = "Description", style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.background)
                }
            }

        }
    }

}

@Composable
fun taskCard() {
    Row(modifier = Modifier.height(50.dp)) {
        radioButtonComponent()

        Row(modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape =  RoundedCornerShape(0.dp, 25.dp, 25.dp, 0.dp)
            )
            .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Column() {
                Text(text = "Task name",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.background)
                Text(text = "Description", style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.background)
            }
        }
    }
}

@Composable
fun radioButtonComponent() {
    Box {
        Row {
            Box(
                modifier = Modifier
                    .size(width = 25.dp, height = 50.dp)
                    .background(color = MaterialTheme.colorScheme.background)
            )
            Box(
                modifier = Modifier
                    .size(width = 25.dp, height = 50.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            )
        }
        Box(
            modifier = Modifier
                .border(
                    BorderStroke(2.dp, MaterialTheme.colorScheme.background),
                    shape = CircleShape
                )
                .size(50.dp)
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    taskCard()
}