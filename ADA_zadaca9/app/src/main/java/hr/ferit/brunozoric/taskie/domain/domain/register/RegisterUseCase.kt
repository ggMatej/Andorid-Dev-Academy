package hr.ferit.brunozoric.taskie.domain.domain.register

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.request.UserDataRequest
import hr.ferit.brunozoric.taskie.data.response.RegisterResponse

interface RegisterUseCase {
    fun execute(body: UserDataRequest, onSuccess: SuccessLambda<RegisterResponse>, onFailure: ErrorLambda)

}