package com.kauabispo.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kauabispo.todolist.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    
    abstract fun taskDao(): TaskDao
    
    companion object {
        @Volatile
        private var instance: TaskDatabase? = null
        
        fun getInstance(context: Context): TaskDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).build().also { instance = it }
            }
        }
    }
}
