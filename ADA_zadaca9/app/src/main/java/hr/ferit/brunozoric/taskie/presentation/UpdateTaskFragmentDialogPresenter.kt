package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.request.UpdateTaskRequest
import hr.ferit.brunozoric.taskie.domain.domain.edittask.EditTaskUseCase
import hr.ferit.brunozoric.taskie.domain.domain.gettask.GetTaskUseCase
import hr.ferit.brunozoric.taskie.ui.fragments.updatetaskfragmentdialog.UpdateTaskFragmentDialogContract

class UpdateTaskFragmentDialogPresenter(private val editTaskUseCase: EditTaskUseCase, private val getTaskUseCase: GetTaskUseCase ): UpdateTaskFragmentDialogContract.Presenter {



    private lateinit var view: UpdateTaskFragmentDialogContract.View

    override fun setView(view: UpdateTaskFragmentDialogContract.View) {
        this.view = view
    }

    override fun updateTask(id: String, title: String, content: String, taskPriority: Int) {
        editTaskUseCase.execute(UpdateTaskRequest(id, title, content, taskPriority),::onTaskUpdated, ::onTaskUpdatingFailed)
    }

    private fun onTaskUpdatingFailed(throwable: Throwable) {
        view.taskEditFail(throwable.localizedMessage)
    }

    private fun onTaskUpdated(task: BackendTask) {
        view.taskUpdated(task)
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