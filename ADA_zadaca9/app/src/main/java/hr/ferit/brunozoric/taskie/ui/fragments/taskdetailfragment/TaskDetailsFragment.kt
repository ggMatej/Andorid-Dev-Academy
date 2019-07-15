package hr.ferit.brunozoric.taskie.ui.fragments.taskdetailfragment

import android.os.Bundle
import android.view.View
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.EXTRA_TASK_ID
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.PriorityColor
import hr.ferit.brunozoric.taskie.ui.fragments.updatetaskfragmentdialog.UpdateTaskFragmentDialog
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_details.*
import org.koin.android.ext.android.inject

class TaskDetailsFragment : BaseFragment(), UpdateTaskFragmentDialog.TaskUpdatedListener, TaskDetailsFragmentContract.View {
    private val presenter by inject<TaskDetailsFragmentContract.Presenter>()

    private var taskID =
        NO_TASK

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_task_details
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        arguments?.getString(EXTRA_TASK_ID)?.let { taskID = it }
        tryDisplayTask(taskID)
        initListener()
    }

    private fun initListener() {
        updateButton.setOnClickListener { updateTask(taskID) }
    }

    private fun updateTask(taskID: String) {
        val dialog = UpdateTaskFragmentDialog.newInstance()
        dialog.taskId = taskID
        dialog.setTaskUpdatedListener(this)
        dialog.show(childFragmentManager,dialog.tag)
    }

    private fun tryDisplayTask(id: String) {
        try {
           presenter.getTask(id)

        } catch (e: NoSuchElementException) {
            context?.displayToast(getString(R.string.noTaskFound))
        }
    }

    override fun onTaskUpdated(task: BackendTask) {
        tryDisplayTask(taskID)
    }


    companion object {
        const val NO_TASK = ""

        fun newInstance(taskId: String): TaskDetailsFragment {
            val bundle = Bundle().apply { putString(EXTRA_TASK_ID, taskId) }
            return TaskDetailsFragment()
                .apply { arguments = bundle }
        }
    }

    override fun onTaskReceived(task: BackendTask) {
        task.run {
            detailsTaskTitle.text=title
            detailsTaskDescription.text=content
            detailsPriorityView.setBackgroundResource(when(taskPriority){
                1 -> PriorityColor.LOW.getColor()
                2-> PriorityColor.MEDIUM.getColor()
                else -> PriorityColor.HIGH.getColor()
            })
        }
    }

    override fun onTaskFailed(error: String) {
        context?.displayToast(error)
    }
}
