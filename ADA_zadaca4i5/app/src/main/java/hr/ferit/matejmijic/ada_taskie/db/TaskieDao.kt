package hr.ferit.matejmijic.ada_taskie.db

import android.app.ActivityManager
import android.os.FileObserver.DELETE
import androidx.room.*
import hr.ferit.matejmijic.ada_taskie.model.Priority
import hr.ferit.matejmijic.ada_taskie.model.Task

@Dao
interface TaskieDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): MutableList<Task>

    @Query("SELECT * FROM tasks WHERE id= :id")
    fun getTask(id: Int): Task

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE from tasks")
    fun deleteAllTasks()

    @Query("UPDATE tasks SET title= :taskTitle, description= :taskDescription, priority= :taskPriority WHERE id= :taskId")
    fun updateTask(taskId: Int, taskTitle: String, taskDescription: String, taskPriority: Priority)

    @Query("SELECT * FROM tasks ORDER BY priority ASC")
    fun getAllTasksOrdered(): MutableList<Task>
}