package hr.ferit.brunozoric.taskie.domain.domain.gettingtasks

import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.repository.GetTasksRepository
import hr.ferit.brunozoric.taskie.data.response.GetTasksResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class GetTasksUseCaseImpl(private val getTasksRepository: GetTasksRepository): GetTasksUseCase {
    override fun execute(onSuccess: SuccessLambda<GetTasksResponse>, onFailure: ErrorLambda) {
        getTasksRepository.getAllTasks().enqueue(object: Callback<GetTasksResponse>{
            override fun onFailure(call: Call<GetTasksResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<GetTasksResponse>, response: Response<GetTasksResponse>) {
                if (response.isSuccessful) response.body()?.run(onSuccess)

                response.errorBody()?.run { onFailure(IllegalStateException("Something went wrong")) }
            }
        })
    }


}