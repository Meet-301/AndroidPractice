package com.example.todolist.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.dao.TasksDao
import com.example.todolist.data.tables.Tasks

@Database(entities = [Tasks::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {

    abstract fun dao() : TasksDao

}