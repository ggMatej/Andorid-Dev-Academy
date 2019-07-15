package hr.ferit.brunozoric.taskie.networking.interactors

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest
import hr.ferit.brunozoric.taskie.model.request.DeleteTaskRequest
import hr.ferit.brunozoric.taskie.model.request.UpdateTaskRequest
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.*
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Callback

class TaskieInteractorImpl(private val apiService: TaskieApiService) : TaskieInteractor {

    override fun editNote(request: UpdateTaskRequest, updateTaskCallback: Callback<BackendTask>) {
        apiService.editNote(request).enqueue(updateTaskCallback)
    }

    override fun delete(id: String, getDeleteTaskCallback: Callback<DeleteTaskResponse>) {
        apiService.deleteTask(id).enqueue(getDeleteTaskCallback)
    }


    override fun getTask(userId: String, getTaskCallback: Callback<BackendTask>) {
        apiService.getTask(userId).enqueue(getTaskCallback)
    }

    override fun getTasks(taskieResponseCallback: Callback<GetTasksResponse>) {
        apiService.getTasks().enqueue(taskieResponseCallback)
    }

    override fun register(request: UserDataRequest, registerCallback: Callback<RegisterResponse>) {
        apiService.register(request).enqueue(registerCallback)
    }

    override fun login(request: UserDataRequest, loginCallback: Callback<LoginResponse>) {
        apiService.login(request).enqueue(loginCallback)
    }

    override fun save(request: AddTaskRequest, saveCallback: Callback<BackendTask>) {
        apiService.save(request).enqueue(saveCallback)
    }
}