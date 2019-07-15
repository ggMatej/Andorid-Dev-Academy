package hr.ferit.brunozoric.taskie.domain.domain.gettask

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.BackendTask

interface GetTaskUseCase {
    fun execute(id: String, onSuccess: SuccessLambda<BackendTask>, onFailure: ErrorLambda)
}