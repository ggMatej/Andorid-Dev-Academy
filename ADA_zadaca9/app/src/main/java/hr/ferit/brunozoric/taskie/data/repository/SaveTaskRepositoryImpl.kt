package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.request.AddTaskRequest
import hr.ferit.brunozoric.taskie.data.response.DeleteTaskResponse
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Call

class SaveTaskRepositoryImpl(private val authService: TaskieApiService): SaveTaskRepository {
    override fun save(body: AddTaskRequest): Call<BackendTask> = authService.save(body)
}