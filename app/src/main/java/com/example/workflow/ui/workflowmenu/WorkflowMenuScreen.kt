package com.example.workflow.ui.workflowmenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workflow.R
import com.example.workflow.ui.workflow.WorkflowScreen

@Composable
fun WorkflowMenuScreen() {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.primary)
        .padding(bottom = 20.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)) {
            Text(text = "ICONO")
            /*TODO poner el icono de la app*/
        }

        Column(modifier = Modifier
            .padding(horizontal = 30.dp)
            .weight(1f)) {
            menuItem("Workflow 1")
            menuItem("Workflow 2")
            menuItem("Workflow 3")
        }

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Image(imageVector = ImageVector.vectorResource(R.drawable.lightbulb_fill),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(35.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background))
            switchComponent()
            Box(modifier = Modifier
                .padding(start = 10.dp)
                .size(35.dp))
        }
    }

}

@Composable
private fun menuItem(
    text: String
){
    Text(text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(top = 20.dp))
}

@Composable
fun switchComponent() {
    Box(modifier = Modifier
        .size(width = 60.dp, height = 30.dp)
        .background(
            color = MaterialTheme.colorScheme.background,
            shape = CircleShape
        )){
        Box(modifier = Modifier
            .size(30.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .border(
                BorderStroke(2.dp, MaterialTheme.colorScheme.background),
                shape = CircleShape
            ))
    }
}

@Preview
@Composable
fun Preview() {
    WorkflowMenuScreen()
}