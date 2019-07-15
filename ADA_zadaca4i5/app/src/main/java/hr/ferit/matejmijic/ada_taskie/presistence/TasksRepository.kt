package hr.ferit.matejmijic.ada_taskie.presistence


import hr.ferit.matejmijic.ada_taskie.model.Priority
import hr.ferit.matejmijic.ada_taskie.model.Task

interface TasksRepository{
    fun addTask(task: Task)
    fun getTasks(): MutableList<Task>
    fun deleteTask(task: Task)
    fun deleteAllTasks()
    fun updateTask(id: Int, title: String, description: String, priority: Priority)
    fun getTask(id: Int): Task
    fun getTasksOrdered(): MutableList<Task>
}