package hr.ferit.brunozoric.taskie.domain.domain.savetask

import android.telecom.Call
import hr.ferit.brunozoric.taskie.common.ErrorLambda
import hr.ferit.brunozoric.taskie.common.SuccessLambda
import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.repository.SaveTaskRepository
import hr.ferit.brunozoric.taskie.data.request.AddTaskRequest
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class SaveTaskUseCaseImpl(private val saveTaskRepository: SaveTaskRepository): SaveTaskUseCase {
    override fun execute(body: AddTaskRequest, onSuccess: SuccessLambda<BackendTask>, onFailure: ErrorLambda) {
        saveTaskRepository.save(body).enqueue(object: Callback<BackendTask>{
            override fun onFailure(call: retrofit2.Call<BackendTask>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: retrofit2.Call<BackendTask>, response: Response<BackendTask>) {
                if (response.isSuccessful) response.body()?.run(onSuccess)

                response.errorBody()?.run { onFailure(IllegalStateException("Something went wrong")) }
            }
        })
    }
}