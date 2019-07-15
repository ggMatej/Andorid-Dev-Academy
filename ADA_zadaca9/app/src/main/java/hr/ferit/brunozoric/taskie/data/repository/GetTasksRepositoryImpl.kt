package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Call

class GetTasksRepositoryImpl(private val authService: TaskieApiService): GetTasksRepository {
    override fun getAllTasks(): Call<GetTasksResponse> = authService.getTasks()
}