package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.request.AddTaskRequest
import retrofit2.Call
import retrofit2.http.Body

interface SaveTaskRepository {
    fun save(body: AddTaskRequest): Call<BackendTask>
}