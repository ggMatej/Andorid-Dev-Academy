package hr.ferit.brunozoric.taskie.domain.domain.deletingtasks

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.response.DeleteTaskResponse

interface DeleteTaskUseCase {
    fun execute(id: String, onSuccess: SuccessLambda<DeleteTaskResponse>,onFailure: ErrorLambda)
}