package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.data.request.UserDataRequest
import hr.ferit.brunozoric.taskie.data.response.RegisterResponse
import retrofit2.Call

interface RegisterRepository {
    fun registerUser(body: UserDataRequest): Call<RegisterResponse>
}