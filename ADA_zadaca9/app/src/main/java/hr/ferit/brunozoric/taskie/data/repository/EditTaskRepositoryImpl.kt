package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.request.UpdateTaskRequest
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Call

class EditTaskRepositoryImpl(private val authService: TaskieApiService): EditTaskRepository {
    override fun editNote(body: UpdateTaskRequest): Call<BackendTask> = authService.editNote(body)
}