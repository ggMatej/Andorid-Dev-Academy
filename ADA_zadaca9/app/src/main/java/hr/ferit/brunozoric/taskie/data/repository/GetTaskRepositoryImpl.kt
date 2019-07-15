package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Call

class GetTaskRepositoryImpl(private val authService: TaskieApiService): GetTaskRepository{
    override fun getTask(someId: String): Call<BackendTask> = authService.getTask(someId)
}