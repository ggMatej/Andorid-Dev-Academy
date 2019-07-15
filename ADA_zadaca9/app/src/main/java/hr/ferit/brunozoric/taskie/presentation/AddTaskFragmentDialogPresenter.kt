package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.request.AddTaskRequest
import hr.ferit.brunozoric.taskie.domain.domain.savetask.SaveTaskUseCase
import hr.ferit.brunozoric.taskie.ui.fragments.addtaskfragmentdialog.AddTaskFragmentDialogContract

class AddTaskFragmentDialogPresenter(private val saveTaskUseCase: SaveTaskUseCase): AddTaskFragmentDialogContract.Presenter {
    private lateinit var view : AddTaskFragmentDialogContract.View

    override fun setView(view: AddTaskFragmentDialogContract.View) {
        this.view = view
    }

    override fun addTask(title: String, content: String, taskPriority: Int) {
        saveTaskUseCase.execute(AddTaskRequest(title, content, taskPriority),::onTaskAdded,::onTaskAddingFailed)
    }

    private fun onTaskAddingFailed(throwable: Throwable) {
        view.onAddingTaskFailed(throwable.localizedMessage)
    }

    private fun onTaskAdded(task: BackendTask) {
        view.onTaskAdded(task)
    }
}