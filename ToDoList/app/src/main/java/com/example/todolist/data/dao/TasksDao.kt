package com.example.todolist.data.dao

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.todolist.data.tables.Tasks

interface TasksDao {

    @Upsert
    fun addEditTask(task: Tasks)

    @Delete
    fun deleteTask(task: Tasks)

    @Query("SELECT * FROM tasks")
    fun getTasks(): List<Tasks>

}