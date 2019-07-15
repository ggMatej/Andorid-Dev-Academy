package hr.ferit.brunozoric.taskie

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.brunozoric.taskie.Taskie.Companion.instance
import hr.ferit.brunozoric.taskie.common.RESPONSE_OK
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.model.request.DeleteTaskRequest
import hr.ferit.brunozoric.taskie.model.response.DeleteTaskResponse
import hr.ferit.brunozoric.taskie.networking.BackendFactory
import hr.ferit.brunozoric.taskie.ui.adapters.TaskAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SwipeToDeleteCallback(private val adapter: TaskAdapter):
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val interactor = BackendFactory.getTaskieInteractor()


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        interactor.delete(adapter.getData(viewHolder.adapterPosition).id, deleteTaskCallback())
        adapter.deleteItem(viewHolder.adapterPosition)
        adapter.notifyDataSetChanged()
    }

    private fun deleteTaskCallback() = object:  Callback<DeleteTaskResponse> {
        override fun onFailure(call: Call<DeleteTaskResponse>, t: Throwable) {
            instance.displayToast("FAIL FAIL")
        }

        override fun onResponse(call: Call<DeleteTaskResponse>, response: Response<DeleteTaskResponse>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse(response)
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleSomethingWentWrong() = instance.displayToast("Something went wrong!")

    private fun handleOkResponse(response: Response<DeleteTaskResponse>) {
        instance.displayToast("Item deleted")

    }
}