package com.example.to_do_list_drag.vmv.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.to_do_list_drag.models.Task
import com.example.to_do_list_drag.models.TaskStatus

class MainViewModel : ViewModel() {
    var isCurrentlyDragging by mutableStateOf(false)
        private set

    val items = mutableStateListOf<Task>()

    var addedTasks = mutableStateListOf<Task>()
        private set
    fun addExampleTasks() {
        items.addAll(listOf(
            Task(1, "Task 1","Description 1", TaskStatus.TO_DO),
            Task(2, "Task 2","Description 2", TaskStatus.IN_PROGRESS),
            Task(3, "Task 3","Description 3",  TaskStatus.DONE)
        ))

    }

    fun startDragging() {
        isCurrentlyDragging = true
    }

    fun stopDragging() {
        isCurrentlyDragging = false
    }

    fun addTask(task: Task) {
        items.add(task)
    }

    fun updateTaskStatus(task: Task, newStatus: TaskStatus) {
        val index = items.indexOfFirst { it.id == task.id }
        if (index != -1) {
            val currentTask = items[index]
            if (currentTask.status != newStatus) {
                println("Updating task ${task.id} from ${currentTask.status} to $newStatus")
                // Actualizar el estado directamente
                items[index] = currentTask.copy(status = newStatus)
            }
        }
    }

}