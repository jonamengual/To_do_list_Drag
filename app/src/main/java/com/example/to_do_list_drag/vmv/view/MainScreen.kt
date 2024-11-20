package com.example.to_do_list_drag.vmv.view

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
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

import com.example.to_do_list_drag.models.Task

import com.example.to_do_list_drag.vmv.viewmodel.MainViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.to_do_list_drag.components.QueueTask
import com.example.to_do_list_drag.models.QueueType

@Composable
fun MainScreen(   modifier: Modifier = Modifier,
                  viewModel: MainViewModel = viewModel()
) {

    val todoTasks = viewModel.todoTasks
    val inProgressTasks = viewModel.inProgressTasks
    val doneTasks = viewModel.doneTasks

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        QueueTask(
            queueType = QueueType.TodoQueue,
            tasks = todoTasks,
            modifier = Modifier.weight(1f),
            onTaskDrop = { task, fromQueueType ->
                viewModel.moveTask(task, fromQueueType, QueueType.TodoQueue)
            }
        )
        QueueTask(
            queueType = QueueType.InProgressQueue,
            tasks = inProgressTasks,
            modifier = Modifier.weight(1f),
            onTaskDrop = { task, fromQueueType ->
                viewModel.moveTask(task, fromQueueType, QueueType.InProgressQueue)
            }
        )
        QueueTask(
            queueType = QueueType.DoneQueue,
            tasks = doneTasks,
            modifier = Modifier.weight(1f),
            onTaskDrop = { task, fromQueueType ->
                viewModel.moveTask(task, fromQueueType, QueueType.DoneQueue)
            }
        )
    }
}



