package hr.ferit.brunozoric.taskie.domain.domain.register

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.repository.RegisterRepository
import hr.ferit.brunozoric.taskie.data.request.UserDataRequest
import hr.ferit.brunozoric.taskie.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class RegisterUseCaseImpl(private val registerRepository: RegisterRepository): RegisterUseCase {
    override fun execute(body: UserDataRequest, onSuccess: SuccessLambda<RegisterResponse>, onFailure: ErrorLambda) {
        registerRepository.registerUser(body).enqueue(object : Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) response.body()?.run(onSuccess)

                response.errorBody()?.run { onFailure(IllegalStateException("Something went wrong")) }
            }
        })
    }
}