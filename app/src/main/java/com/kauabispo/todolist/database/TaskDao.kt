package com.kauabispo.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kauabispo.todolist.model.Task

@Dao
interface TaskDao {
    
    @Insert
    suspend fun insert(task: Task)
    
    @Update
    suspend fun update(task: Task)
    
    @Delete
    suspend fun delete(task: Task)
    
    @Query("SELECT * FROM tasks ORDER BY completed ASC, priority DESC, due_date ASC")
    fun getAllTasks(): LiveData<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Task?
    
    @Query("SELECT * FROM tasks WHERE completed = 0 ORDER BY priority DESC, due_date ASC")
    fun getActiveTasks(): LiveData<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE completed = 1 ORDER BY due_date DESC")
    fun getCompletedTasks(): LiveData<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE category = :category ORDER BY priority DESC")
    fun getTasksByCategory(category: String): LiveData<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :query || '%' ORDER BY priority DESC")
    fun searchTasks(query: String): LiveData<List<Task>>
    
    @Query("DELETE FROM tasks WHERE completed = 1")
    suspend fun deleteCompletedTasks()
    
    @Query("SELECT COUNT(*) FROM tasks")
    suspend fun getTaskCount(): Int
    
    @Query("SELECT COUNT(*) FROM tasks WHERE completed = 1")
    suspend fun getCompletedTaskCount(): Int
}
