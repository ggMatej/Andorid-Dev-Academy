package hr.ferit.brunozoric.taskie.ui.fragments.taskfragment

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.brunozoric.taskie.Taskie.Companion.instance
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.ui.adapters.TaskAdapter
import org.koin.core.KoinComponent
import org.koin.core.inject

class SwipeToDeleteCallback(private val adapter: TaskAdapter):
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT), SwipeToDeleteCallbackContract.View, KoinComponent{

    private val presenter by inject<SwipeToDeleteCallbackContract.Presenter>()

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        presenter.setView(this)
        presenter.delteTask(adapter.getData(viewHolder.adapterPosition).id)
        adapter.deleteItem(viewHolder.adapterPosition)
        adapter.notifyDataSetChanged()
    }

    override fun onTaskDelted() {
        instance.displayToast("Item deleted")
    }

    override fun showError(error: String) {
        instance.displayToast("Something went wrong!")
    }
}