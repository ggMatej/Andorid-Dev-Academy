package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.request.UpdateTaskRequest
import retrofit2.Call
import retrofit2.http.Body

interface EditTaskRepository {
    fun editNote(body: UpdateTaskRequest): Call<BackendTask>
}