package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.repository.GetTaskRepository
import hr.ferit.brunozoric.taskie.domain.domain.gettask.GetTaskUseCase
import hr.ferit.brunozoric.taskie.ui.fragments.taskdetailfragment.TaskDetailsFragmentContract

class TaskDetailsFragmentPresenter(private val getTaskUseCase: GetTaskUseCase): TaskDetailsFragmentContract.Presenter {
    private lateinit var view: TaskDetailsFragmentContract.View

    override fun setView(view: TaskDetailsFragmentContract.View) {
        this.view = view
    }

    override fun getTask(id: String) {
        getTaskUseCase.execute(id, ::onTaskReceived, ::onTaskFailed)
    }

    private fun onTaskFailed(throwable: Throwable) {
        view.onTaskFailed(throwable.localizedMessage)
    }

    private fun onTaskReceived(backendTask: BackendTask) {
        view.onTaskReceived(backendTask)
    }
}