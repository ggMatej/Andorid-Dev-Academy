package hr.ferit.brunozoric.taskie.domain.domain.login

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.request.UserDataRequest
import hr.ferit.brunozoric.taskie.data.response.LoginResponse

interface LoginUseCase {
    fun execute(body: UserDataRequest, onSuccess: SuccessLambda<LoginResponse>, onFailure: ErrorLambda)
}