package hr.ferit.brunozoric.taskie.domain.domain.savetask

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.request.AddTaskRequest
import java.lang.Error

interface SaveTaskUseCase {
    fun execute(body: AddTaskRequest, onSuccess: SuccessLambda<BackendTask>, onFailure: ErrorLambda)
}