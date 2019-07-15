package hr.ferit.matejmijic.ada_taskie.ui.swipedelete

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.matejmijic.ada_taskie.presistence.TaskRoomRepository
import hr.ferit.matejmijic.ada_taskie.presistence.TasksRepository
import hr.ferit.matejmijic.ada_taskie.ui.adapters.TaskAdapter
import hr.ferit.matejmijic.ada_taskie.ui.fragments.TasksFragment

class SwipeToDeleteCallback(private val adapter: TaskAdapter):
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private var repository = TaskRoomRepository()




    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        repository.deleteTask(adapter.getTaskAt(viewHolder.adapterPosition))
        adapter.deleteItem(viewHolder.adapterPosition)
        adapter.notifyDataSetChanged()
    }
}