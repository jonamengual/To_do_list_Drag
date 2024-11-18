package com.example.to_do_list_drag.models

data class Task (val id: Int, val title: String, val description: String, var status: TaskStatus)

enum class TaskStatus{
    TO_DO,IN_PROGRESS,DONE
}