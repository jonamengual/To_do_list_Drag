package com.example.to_do_list_drag.vmv.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.to_do_list_drag.models.QueueType
import com.example.to_do_list_drag.models.Task


class MainViewModel : ViewModel() {

    val todoTasks = mutableStateListOf<Task>(
        Task(id = 1, title = "Task 1", description = "Description 1"),
        Task(id = 2, title = "Task 4", description = "Description 4"),
    )

    val inProgressTasks = mutableStateListOf<Task>(
        Task(id = 3, title = "Task 2", description = "Description 2"),
        Task(id = 4, title = "Task 5", description = "Description 5"),
    )

    val doneTasks = mutableStateListOf<Task>(
        Task(id = 5, title = "Task 3", description = "Description 3"),
    )

    fun moveTask(
        task: Task,
        from: QueueType,
        to: QueueType
    ) {
        when(from) {
            QueueType.TodoQueue -> todoTasks.remove(task)
            QueueType.InProgressQueue -> inProgressTasks.remove(task)
            QueueType.DoneQueue -> doneTasks.remove(task)
        }
        when(to) {
            QueueType.TodoQueue -> todoTasks.add(task)
            QueueType.InProgressQueue -> inProgressTasks.add(task)
            QueueType.DoneQueue -> doneTasks.add(task)
        }
    }
}