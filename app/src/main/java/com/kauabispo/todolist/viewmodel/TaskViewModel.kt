package com.kauabispo.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kauabispo.todolist.database.TaskDao
import com.kauabispo.todolist.model.Task
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()
    val activeTasks: LiveData<List<Task>> = taskDao.getActiveTasks()
    val completedTasks: LiveData<List<Task>> = taskDao.getCompletedTasks()
    
    fun insertTask(task: Task) = viewModelScope.launch {
        taskDao.insert(task)
    }
    
    fun updateTask(task: Task) = viewModelScope.launch {
        taskDao.update(task)
    }
    
    fun deleteTask(task: Task) = viewModelScope.launch {
        taskDao.delete(task)
    }
    
    fun clearCompletedTasks() = viewModelScope.launch {
        taskDao.deleteCompletedTasks()
    }
    
    fun searchTasks(query: String): LiveData<List<Task>> {
        return taskDao.searchTasks(query)
    }
    
    fun getTasksByCategory(category: String): LiveData<List<Task>> {
        return taskDao.getTasksByCategory(category)
    }
    
    class Factory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                return TaskViewModel(taskDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
