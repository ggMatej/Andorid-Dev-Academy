package hr.ferit.brunozoric.taskie.domain.domain.gettingtasks

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.response.GetTasksResponse

interface GetTasksUseCase {

    fun execute(onSuccess: SuccessLambda<GetTasksResponse>,onFailure: ErrorLambda)
}