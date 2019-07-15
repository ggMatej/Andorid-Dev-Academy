package hr.ferit.brunozoric.taskie.domain.domain.deletingtasks

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.repository.DeleteTaskRepository
import hr.ferit.brunozoric.taskie.data.response.DeleteTaskResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class DeleteTaskUseCaseImpl(private val deleteTaskRepository: DeleteTaskRepository): DeleteTaskUseCase {
    override fun execute(id: String, onSuccess: SuccessLambda<DeleteTaskResponse>, onFailure: ErrorLambda) {
        deleteTaskRepository.deleteTask(id).enqueue(object: Callback<DeleteTaskResponse> {
            override fun onFailure(call: Call<DeleteTaskResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<DeleteTaskResponse>, response: Response<DeleteTaskResponse>) {
                if (response.isSuccessful) response.body()?.run(onSuccess)

                response.errorBody()?.run { onFailure(IllegalStateException("Something went wrong")) }
            }
        })
    }
}