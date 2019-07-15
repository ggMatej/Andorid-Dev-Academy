package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.request.UserDataRequest
import hr.ferit.brunozoric.taskie.data.response.LoginResponse
import retrofit2.Call

interface LoginRepository {
    fun loginUser(body: UserDataRequest): Call<LoginResponse>
}