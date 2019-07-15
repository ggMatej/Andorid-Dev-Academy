package hr.ferit.matejmijic.ada_taskie.ui.fragments

import android.os.Bundle
import android.view.View
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.common.EXTRA_TASK_ID
import hr.ferit.matejmijic.ada_taskie.common.displayToast
import hr.ferit.matejmijic.ada_taskie.common.gone
import hr.ferit.matejmijic.ada_taskie.common.invisible
import hr.ferit.matejmijic.ada_taskie.model.Task
import hr.ferit.matejmijic.ada_taskie.presistence.TaskRoomRepository
import hr.ferit.matejmijic.ada_taskie.presistence.TasksRepository
import hr.ferit.matejmijic.ada_taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_task_details.*

class TaskDetailsFragment: BaseFragment(), UpdateTaskFragmentDialog.TaskUpdatedListener {

    private val repository: TasksRepository = TaskRoomRepository()
    private var taskID = NO_TASK


    override fun onTaskUpdated(task: Task) {
        displayTask(task)

    }



    override fun getLayoutResourceId(): Int = R.layout.fragment_task_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(EXTRA_TASK_ID)?.let {taskID = it }

        tryToDisplayTask(taskID)
        setListeners()
    }

    private fun setListeners() {
        updateButton.setOnClickListener { updateTask(taskID) }
    }

    private fun updateTask(taskID: Int) {
        val dialog = UpdateTaskFragmentDialog.newInstance()
        dialog.taskID = taskID
        dialog.task = repository.getTask(taskID)
        dialog.setTaskAddedListener(this)
        dialog.show(childFragmentManager,dialog.tag)
    }

    private fun tryToDisplayTask(id: Int) {
        try {
            val task = repository.getTask(id)
            displayTask(task)
        } catch (e: NoSuchElementException){
            context?.displayToast(R.string.noTaskFound)
        }
    }

    private fun displayTask(task: Task) {
        detailsTaskTitle.text = task.title
        detailsTaskDescription.text = task.description
        detailsPriorityView.setBackgroundResource(task.priority.getColor())

    }



    companion object {
        const val NO_TASK = -1

        fun newInstance(taskId: Int): TaskDetailsFragment {
            val bundle = Bundle().apply { putInt(EXTRA_TASK_ID,taskId) }
            return TaskDetailsFragment().apply { arguments = bundle }
        }
    }
}