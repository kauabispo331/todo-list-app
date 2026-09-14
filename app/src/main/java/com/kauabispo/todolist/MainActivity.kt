package com.kauabispo.todolist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.fab.FloatingActionButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kauabispo.todolist.adapter.TaskAdapter
import com.kauabispo.todolist.database.TaskDatabase
import com.kauabispo.todolist.model.Task
import com.kauabispo.todolist.viewmodel.TaskViewModel
import com.kauabispo.todolist.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title = "Minhas Tarefas"

        // Initialize ViewModel
        val database = TaskDatabase.getInstance(this)
        val viewModelFactory = TaskViewModel.Factory(database.taskDao())
        viewModel = ViewModelProvider(this, viewModelFactory).get(TaskViewModel::class.java)

        // Setup RecyclerView
        taskAdapter = TaskAdapter(
            onTaskClick = { task -> editTask(task) },
            onTaskDelete = { task -> deleteTask(task) },
            onTaskComplete = { task -> completeTask(task) }
        )
        binding.recyclerViewTasks.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        // Observe tasks
        viewModel.allTasks.observe(this) { tasks ->
            taskAdapter.submitList(tasks)
            updateEmptyState(tasks.isEmpty())
        }

        // Setup FAB
        binding.fabAddTask.setOnClickListener {
            addNewTask()
        }
    }

    private fun addNewTask() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Nova Tarefa")
            .setMessage("Qual é o título da tarefa?")
            .setNeutralButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("Adicionar") { dialog, _ ->
                // Implementation for adding task
                dialog.dismiss()
            }
            .show()
    }

    private fun editTask(task: Task) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Editar Tarefa")
            .setMessage("Editando: ${task.title}")
            .setNeutralButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("Salvar") { dialog, _ ->
                // Implementation for editing task
                dialog.dismiss()
            }
            .show()
    }

    private fun deleteTask(task: Task) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Deletar Tarefa")
            .setMessage("Tem certeza que deseja deletar '${task.title}'?")
            .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("Deletar") { dialog, _ ->
                viewModel.deleteTask(task)
                dialog.dismiss()
            }
            .show()
    }

    private fun completeTask(task: Task) {
        val updatedTask = task.copy(completed = !task.completed)
        viewModel.updateTask(updatedTask)
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyStateText.visibility = if (isEmpty) {
            android.view.View.VISIBLE
        } else {
            android.view.View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // Open settings
                true
            }
            R.id.action_clear_all -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Limpar Tudo")
                    .setMessage("Tem certeza que deseja deletar todas as tarefas concluídas?")
                    .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
                    .setPositiveButton("Limpar") { dialog, _ ->
                        viewModel.clearCompletedTasks()
                        dialog.dismiss()
                    }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
