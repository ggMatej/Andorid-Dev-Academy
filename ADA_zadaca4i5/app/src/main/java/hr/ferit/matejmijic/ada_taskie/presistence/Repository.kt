package hr.ferit.matejmijic.ada_taskie.presistence

import hr.ferit.matejmijic.ada_taskie.model.Priority
import hr.ferit.matejmijic.ada_taskie.model.Task

object Repository {
    private val tasks = mutableListOf<Task>()
    private var currentId = 0

    public fun save(title: String, description: String, priority: Priority): Task {
        val task =
            Task(currentId, title, description, priority)
        task.id = currentId
        tasks.add(task)
        currentId++
        return task
    }

    fun deleteBy(id: Int){
        tasks.removeAll { (it.id) == id }
    }

    fun count() = tasks.size

    fun get(id: Int): Task {
        return tasks.first { it.id == id }
    }

    fun getAllTasks() = tasks
}