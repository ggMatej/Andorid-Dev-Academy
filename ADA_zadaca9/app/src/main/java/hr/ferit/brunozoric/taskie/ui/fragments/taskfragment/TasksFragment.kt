package hr.ferit.brunozoric.taskie.ui.fragments.taskfragment

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.*
import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.response.GetTasksResponse
import hr.ferit.brunozoric.taskie.ui.activities.ContainerActivity
import hr.ferit.brunozoric.taskie.ui.adapters.TaskAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.addtaskfragmentdialog.AddTaskFragmentDialog
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tasks.*
import org.koin.android.ext.android.inject

class TasksFragment : BaseFragment(), AddTaskFragmentDialog.TaskAddedListener, TaskFragmentContract.View{

    private val presenter by inject<TaskFragmentContract.Presenter>()
    private val adapter by lazy { TaskAdapter { onItemSelected(it) } }

    private val itemTouchHelper = ItemTouchHelper(
        SwipeToDeleteCallback(
            adapter
        )
    )

    companion object {
        fun newInstance(): Fragment {
            return TasksFragment()
        }

    }

    override fun getLayoutResourceId() = R.layout.fragment_tasks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        setHasOptionsMenu(true)
        initUi()
        initListeners()
    }

    override fun onResume() {
        super.onResume()
        getAllTasks()
    }

    private fun initUi() {
        progress.visible()
        noData.visible()
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        tasksRecyclerView.adapter = adapter
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView)
        getAllTasks()
    }

    private fun initListeners() {
        addTask.setOnClickListener { addTask() }
    }

    private fun onItemSelected(task: BackendTask) {
        val detailsIntent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_TASK_DETAILS)
            putExtra(EXTRA_TASK_ID, task.id)
        }
        startActivity(detailsIntent)
    }

    override fun onTaskAdded(task: BackendTask) {
        adapter.addData(task)
    }

    private fun addTask() {
        val dialog = AddTaskFragmentDialog.newInstance()
        dialog.setTaskAddedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun getAllTasks() {
        progress.visible()
        presenter.getTasks()
    }

    override fun onTasksReceived(response: GetTasksResponse) {
        response.notes?.run {
            checkList(this)
            adapter.setData(this)
        }
    }


    override fun onFailure(error: String) {
        context?.displayToast(error)
    }

    private fun checkList(notes: MutableList<BackendTask>) {
        if (notes.isEmpty()) {
            noData.visible()
            progress.visible()
        } else {
            noData.gone()
            progress.gone()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.refresh -> {
                getAllTasks()
                context?.displayToast("Task refreshed!")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.mymenu,menu)
    }


}
