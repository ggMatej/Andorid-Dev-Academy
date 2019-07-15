package hr.ferit.matejmijic.ada_taskie.presistence

import hr.ferit.matejmijic.ada_taskie.Taskie
import hr.ferit.matejmijic.ada_taskie.db.DaoProvider
import hr.ferit.matejmijic.ada_taskie.model.Priority
import hr.ferit.matejmijic.ada_taskie.model.Task

class TaskRoomRepository: TasksRepository{
    private var db = DaoProvider.getInstance(Taskie.getAppContext())

    private var taskDao = db.taskieDao()

    override fun getTask(id: Int): Task {
       return taskDao.getTask(id)
    }

    override fun addTask(task: Task) {
        taskDao.insertTask(task)
    }

    override fun getTasks(): MutableList<Task> {
        return taskDao.getAllTasks()
    }

    override fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    override fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

    override fun updateTask(id: Int, title: String, description: String, priority: Priority) {
        taskDao.updateTask(id,title,description,priority)
    }

    override fun getTasksOrdered(): MutableList<Task> {
        return taskDao.getAllTasksOrdered()
    }
}