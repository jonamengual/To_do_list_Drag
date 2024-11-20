package com.example.to_do_list_drag.models

enum class QueueType(val title: String) {
    TodoQueue("To Do"),
    InProgressQueue("In Progress"),
    DoneQueue("Done")
}