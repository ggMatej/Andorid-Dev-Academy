package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.data.response.DeleteTaskResponse
import hr.ferit.brunozoric.taskie.domain.domain.deletingtasks.DeleteTaskUseCase
import hr.ferit.brunozoric.taskie.ui.fragments.taskfragment.SwipeToDeleteCallbackContract

class SwipeToDeleteCallbackPresenter(private val deleteTaskUseCase: DeleteTaskUseCase): SwipeToDeleteCallbackContract.Presenter {
    private lateinit var view: SwipeToDeleteCallbackContract.View

    override fun setView(view: SwipeToDeleteCallbackContract.View) {
        this.view = view
    }

    override fun delteTask(id: String) {
        deleteTaskUseCase.execute(id, ::onSucces, ::onDeleteFailed)
    }

    private fun onDeleteFailed(throwable: Throwable) {
        view.showError(throwable.localizedMessage)
    }

    private fun onSucces(response: DeleteTaskResponse) {
        view.onTaskDelted()
    }


}