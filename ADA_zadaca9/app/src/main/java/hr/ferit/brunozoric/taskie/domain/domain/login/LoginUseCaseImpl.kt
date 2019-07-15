package hr.ferit.brunozoric.taskie.domain.domain.login

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.repository.LoginRepository
import hr.ferit.brunozoric.taskie.data.request.UserDataRequest
import hr.ferit.brunozoric.taskie.data.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class LoginUseCaseImpl(private val loginRepository: LoginRepository) :
    LoginUseCase {
    override fun execute(body: UserDataRequest, onSuccess: SuccessLambda<LoginResponse>, onFailure: ErrorLambda) {
        loginRepository.loginUser(body).enqueue(object: Callback<LoginResponse> {

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) response.body()?.run(onSuccess)

                response.errorBody()?.run { onFailure(IllegalStateException("Something went wrong")) }
            }
        })


    }
}