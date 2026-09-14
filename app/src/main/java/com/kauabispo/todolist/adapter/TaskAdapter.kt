package com.kauabispo.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kauabispo.todolist.model.Task
import com.kauabispo.todolist.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskAdapter(
    private val onTaskClick: (Task) -> Unit,
    private val onTaskDelete: (Task) -> Unit,
    private val onTaskComplete: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding, onTaskClick, onTaskDelete, onTaskComplete)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TaskViewHolder(
        private val binding: ItemTaskBinding,
        private val onTaskClick: (Task) -> Unit,
        private val onTaskDelete: (Task) -> Unit,
        private val onTaskComplete: (Task) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.apply {
                taskTitle.text = task.title
                taskTitle.alpha = if (task.completed) 0.5f else 1.0f
                
                taskDescription.text = task.description
                taskDescription.alpha = if (task.completed) 0.5f else 1.0f
                
                checkboxTask.isChecked = task.completed
                
                priorityBadge.text = when (task.priority) {
                    5 -> "★★★★★"
                    4 -> "★★★★"
                    3 -> "★★★"
                    2 -> "★★"
                    else -> "★"
                }
                
                if (task.dueDate != null) {
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    dueDateText.text = dateFormat.format(Date(task.dueDate))
                }
                
                checkboxTask.setOnClickListener {
                    onTaskComplete(task)
                }
                
                root.setOnClickListener {
                    onTaskClick(task)
                }
                
                buttonDelete.setOnClickListener {
                    onTaskDelete(task)
                }
            }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) =
            oldItem == newItem
    }
}
