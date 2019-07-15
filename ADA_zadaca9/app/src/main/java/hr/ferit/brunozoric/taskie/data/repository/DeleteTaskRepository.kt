package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.response.DeleteTaskResponse
import retrofit2.Call

interface DeleteTaskRepository {
    fun deleteTask(id: String): Call<DeleteTaskResponse>
}