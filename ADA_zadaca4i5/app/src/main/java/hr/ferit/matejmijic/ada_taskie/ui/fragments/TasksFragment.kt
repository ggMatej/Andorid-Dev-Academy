package hr.ferit.matejmijic.ada_taskie.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.common.*
import hr.ferit.matejmijic.ada_taskie.ui.swipedelete.SwipeToDeleteCallback
import hr.ferit.matejmijic.ada_taskie.model.Task
import hr.ferit.matejmijic.ada_taskie.presistence.TaskRoomRepository
import hr.ferit.matejmijic.ada_taskie.presistence.TasksRepository
import hr.ferit.matejmijic.ada_taskie.ui.activities.ContainerActivity
import hr.ferit.matejmijic.ada_taskie.ui.adapters.TaskAdapter
import hr.ferit.matejmijic.ada_taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tasks.*

class TasksFragment: BaseFragment(), AddTaskFragmentDialog.TaskAddedListener{

    private val adapter by lazy { TaskAdapter {onItemSelected(it)} }
    private val repository: TasksRepository = TaskRoomRepository()

    private val itemTouchHelper = ItemTouchHelper(
        SwipeToDeleteCallback(
            adapter
        )
    )

    override fun onResume() {
        super.onResume()
        refreshTasks()
    }
    override fun getLayoutResourceId(): Int = R.layout.fragment_tasks


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initUi()
        initListeners()
        refreshTasks()
    }

    private fun initUi() {
        progress.visible()
        noData.visible()
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        tasksRecyclerView.adapter = adapter
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView)
    }

    private fun initListeners() {
        addTask.setOnClickListener{ addTask() }
    }

    private fun refreshTasks() {
        progress.gone()
        val data = repository.getTasks()
        if (data.isNotEmpty()) {
            noData.gone()
        } else {
            noData.visible()
        }
        adapter.setData(data)
    }

    private fun onItemSelected(task: Task) {
        val detailsIntent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_TASK_DETAILS)
            putExtra(EXTRA_TASK_ID, task.id)
        }
        startActivity(detailsIntent)
    }

    private fun addTask(){
        val dialog = AddTaskFragmentDialog.newInstance()
        dialog.setTaskAddedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun onTaskAdded(task: Task) {
        refreshTasks()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sort -> {
                sortTasks()
                return true
            }
            R.id.delete -> {
                deleteTasks()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun refreshTasksOrdered() {
        progress.gone()
        val data = repository.getTasksOrdered()
        if (data.isNotEmpty()) {
            noData.gone()
        } else {
            noData.visible()
        }
        adapter.setDataOrdered(data)
    }

    private fun sortTasks() {
        repository.getTasks()
        refreshTasksOrdered()
    }

    private fun deleteTasks() {
        repository.deleteAllTasks()
        refreshTasks()
    }

    companion object {
        fun newInstance(): Fragment {
            return TasksFragment()
        }
    }
}