package hr.ferit.brunozoric.taskie.networking

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest
import hr.ferit.brunozoric.taskie.model.request.DeleteTaskRequest
import hr.ferit.brunozoric.taskie.model.request.UpdateTaskRequest
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.*
import retrofit2.Call
import retrofit2.http.*


interface TaskieApiService {

    @POST("/api/register")
    fun register(@Body userData: UserDataRequest): Call<RegisterResponse>

    @POST("/api/login")
    fun login(@Body userData: UserDataRequest): Call<LoginResponse>

    @GET("/api/note")
    fun getTasks(): Call<GetTasksResponse>

    @POST("/api/note")
    fun save(@Body taskData: AddTaskRequest): Call<BackendTask>

    @GET("/api/note/{someId}")
    fun getTask(@Path("someId") someId: String): Call<BackendTask>

    @POST("/api/note/delete")
    fun deleteTask(@Query ("id")id: String): Call<DeleteTaskResponse>

    @POST("/api/note/edit")
    fun editNote(@Body updateTaskRequest: UpdateTaskRequest): Call<BackendTask>
}