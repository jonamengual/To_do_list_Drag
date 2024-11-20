package com.example.to_do_list_drag.components

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.to_do_list_drag.models.QueueType
import com.example.to_do_list_drag.models.Task

data class DraggedData(
    val task: Task,
    val originQueue: QueueType,
)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskCard(
    task: Task,
    queueType: QueueType,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .dragAndDropSource {
                detectTapGestures(
                    onLongPress = {
                        startTransfer(
                            DragAndDropTransferData(
                                ClipData.newPlainText("Task", "Task"),
                                localState = DraggedData(task, queueType)
                            )
                        )
                    }
                )
            }
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = task.title)
        Text(text = task.description)
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QueueTask(
    queueType: QueueType,
    tasks: List<Task>,
    modifier: Modifier = Modifier,
    onTaskDrop: (Task, QueueType) -> Unit
) {
    val dropTarget = remember {
        object : DragAndDropTarget {
            override fun onDrop(dragStartEvent: DragAndDropEvent): Boolean {
                val dropEvent = dragStartEvent.toAndroidDragEvent()
                val draggedTaskData = dropEvent.localState as? DraggedData
                val droppedTask = draggedTaskData?.task
                droppedTask?.let { onTaskDrop(it, draggedTaskData.originQueue) }
                return true
            }
        }
    }

    Column(
        modifier = modifier
            .dragAndDropTarget (
                shouldStartDragAndDrop = { dragStartEvent ->
                    dragStartEvent.mimeTypes().contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                }, target = dropTarget
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = queueType.title)
        Spacer(modifier = Modifier.height(4.dp))
        tasks.forEach { task ->
            key(task.id) {
                TaskCard(
                    task = task,
                    queueType = queueType,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}