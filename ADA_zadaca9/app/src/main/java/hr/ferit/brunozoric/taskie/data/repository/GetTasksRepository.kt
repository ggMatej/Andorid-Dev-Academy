package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.response.GetTasksResponse
import retrofit2.Call
import retrofit2.http.Path

interface GetTasksRepository {
    fun getAllTasks(): Call<GetTasksResponse>
}