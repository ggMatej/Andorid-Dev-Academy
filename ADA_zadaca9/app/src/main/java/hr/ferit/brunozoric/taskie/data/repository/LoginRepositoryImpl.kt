package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.request.UserDataRequest
import hr.ferit.brunozoric.taskie.data.response.LoginResponse
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Call

class LoginRepositoryImpl(private val authService: TaskieApiService): LoginRepository {
    override fun loginUser(body: UserDataRequest): Call<LoginResponse> = authService.login(body)
}