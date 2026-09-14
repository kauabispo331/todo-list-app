package com.kauabispo.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    
    @ColumnInfo(name = "title")
    val title: String,
    
    @ColumnInfo(name = "description")
    val description: String = "",
    
    @ColumnInfo(name = "priority")
    val priority: Int = 1, // 1-5, sendo 5 a mais alta
    
    @ColumnInfo(name = "completed")
    val completed: Boolean = false,
    
    @ColumnInfo(name = "due_date")
    val dueDate: Long? = null,
    
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    
    @ColumnInfo(name = "category")
    val category: String = "Geral"
) : Serializable
