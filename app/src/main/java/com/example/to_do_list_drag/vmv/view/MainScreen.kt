package com.example.to_do_list_drag.vmv.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.to_do_list_drag.DragTrarget
import com.example.to_do_list_drag.DropItem
import com.example.to_do_list_drag.models.Task
import com.example.to_do_list_drag.models.TaskStatus
import com.example.to_do_list_drag.vmv.viewmodel.MainViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {
    // Observar directamente el estado de items
    val tasks = mainViewModel.items
    // Llamar a un método para añadir tareas de ejemplo (solo si es necesario)
    LaunchedEffect(Unit) {
        if (tasks.isEmpty()) {
            mainViewModel.addExampleTasks()
        }
    }
    Row(modifier = Modifier.fillMaxSize()) {
        TaskColumn(
            title = "To Do",
            tasks = tasks.filter { it.status == TaskStatus.TO_DO },
            onTaskDropped = { task ->
                mainViewModel.updateTaskStatus(task, TaskStatus.TO_DO)
            },
            viewModel = mainViewModel,
            modifier = Modifier.weight(1f)
        )
        TaskColumn(
            title = "In Progress",
            tasks = tasks.filter { it.status == TaskStatus.IN_PROGRESS },
            onTaskDropped = { task ->
                mainViewModel.updateTaskStatus(task, TaskStatus.IN_PROGRESS)
            },
            viewModel = mainViewModel,
            modifier = Modifier.weight(1f)
        )
        TaskColumn(
            title = "Done",
            tasks = tasks.filter { it.status == TaskStatus.DONE },
            onTaskDropped = { task ->
                mainViewModel.updateTaskStatus(task, TaskStatus.DONE)
            },
            viewModel = mainViewModel,
            modifier = Modifier.weight(1f)
        )
    }
}
@Composable
fun TaskColumn(
    title: String,
    tasks: List<Task>,
    onTaskDropped: (Task) -> Unit,
    viewModel: MainViewModel,  // Asegúrate de recibir el ViewModel
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(8.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
    ) {
        Text(title, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))

        tasks.forEach { task ->
            DragTrarget(
                modifier = Modifier.wrapContentWidth().padding(4.dp),
                datatoDrop = task,
                viewModel = viewModel  // Pasar el ViewModel aquí
            ) {
                TaskItem(task)
            }
        }

        DropItem<Task>(
            modifier = Modifier.fillMaxSize()
        ) { isInBound, droppedTask ->
            if (isInBound && droppedTask != null) {
                println("Task dropped: ${droppedTask.title} to $title")
                onTaskDropped(droppedTask)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp)) // Aquí se aplica el radio
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)) // Asegúrate de aplicar el mismo shape al borde
            .padding(8.dp)
    ) {
        Column {
            Text("${task.title}:")
            Text("${task.description}")
        }
    }
}