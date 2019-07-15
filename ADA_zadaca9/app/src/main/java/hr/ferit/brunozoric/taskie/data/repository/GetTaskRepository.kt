package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.BackendTask
import retrofit2.Call
import retrofit2.http.Path

interface GetTaskRepository {
    fun getTask(someId: String): Call<BackendTask>
}