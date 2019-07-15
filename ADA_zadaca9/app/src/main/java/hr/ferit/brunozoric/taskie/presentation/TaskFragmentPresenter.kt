package hr.ferit.brunozoric.taskie.presentation

import android.view.View
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.gone
import hr.ferit.brunozoric.taskie.common.visible
import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.domain.domain.gettingtasks.GetTasksUseCase
import hr.ferit.brunozoric.taskie.ui.fragments.taskfragment.TaskFragmentContract

class TaskFragmentPresenter(private val getTasksUseCase: GetTasksUseCase) : TaskFragmentContract.Presenter {
    private lateinit var view: TaskFragmentContract.View

    override fun setView(view: TaskFragmentContract.View) {
        this.view = view
    }

    override fun getTasks() {
        getTasksUseCase.execute(::onTasksReceived,::onTaskFailed)
    }


    private fun onTaskFailed(throwable: Throwable) =
        view.onFailure(throwable.localizedMessage)


    private fun onTasksReceived(response: GetTasksResponse){
        view.onTasksReceived(response)
    }

}