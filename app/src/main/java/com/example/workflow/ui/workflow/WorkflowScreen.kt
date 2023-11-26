package com.example.workflow.ui.workflow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WorkflowScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
        .padding(top = 40.dp)) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .padding(20.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically,) {
                    Text(text = "Workflow 1",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f))
                    statusCircularBarAndText()

                }
                Column {
                    stateColumn("New", 2, false)
                    stateColumn("In progress", 3, false)
                    stateColumn("Complited", 1, true)
                }
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End) {
                Box(modifier = Modifier
                    .size(50.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = CircleShape),
                    contentAlignment = Alignment.Center) {
                    Icon( imageVector = Icons.Filled.Add,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.background)
                }
            }

        }
    }

}

@Composable
private fun statusCircularBarAndText(){
    Box(contentAlignment = Alignment.Center) {
        Text(text = "70%", style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary)
        CircularProgressIndicator(progress = 0.7f,
            modifier = Modifier.size(50.dp),
            strokeWidth = 1.dp
        )
    }
}

@Composable
private fun stateColumn(
    stateTitle: String,
    numTasks: Int,
    isComplited: Boolean
) {
    Text(text = stateTitle, style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 20.dp))

    LazyColumn() {
        items(numTasks){
            taskCard(isComplited)
        }
    }
}

@Composable
private fun taskCard(
    isComplited: Boolean
) {
    var textColor = MaterialTheme.colorScheme.background
    var backgroundColor = MaterialTheme.colorScheme.primary

    if (isComplited) {
        textColor = MaterialTheme.colorScheme.primary
        backgroundColor = MaterialTheme.colorScheme.background
    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(10.dp))

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(
            color = backgroundColor,
            shape = CircleShape
        )
        .border(
            BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            shape = CircleShape
        )
        .padding(vertical = 10.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column() {
            Text(text = "Task name",
                style = MaterialTheme.typography.bodyMedium,
                color = textColor)
            Text(text = "Description", style = MaterialTheme.typography.bodySmall,
                color = textColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    WorkflowScreen()
}