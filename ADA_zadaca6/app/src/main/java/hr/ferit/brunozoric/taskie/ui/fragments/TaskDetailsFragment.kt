package hr.ferit.brunozoric.taskie.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.Taskie.Companion.instance
import hr.ferit.brunozoric.taskie.common.EXTRA_TASK_ID
import hr.ferit.brunozoric.taskie.common.RESPONSE_OK
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.model.BackendPriorityTask
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.PriorityColor
import hr.ferit.brunozoric.taskie.model.Task
import hr.ferit.brunozoric.taskie.model.response.GetTaskResponse
import hr.ferit.brunozoric.taskie.networking.BackendFactory
import hr.ferit.brunozoric.taskie.persistence.Repository
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_details.*
import kotlinx.android.synthetic.main.item_task.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskDetailsFragment : BaseFragment(), UpdateTaskFragmentDialog.TaskUpdatedListener {

    private val interactor = BackendFactory.getTaskieInteractor()

    private var taskID = NO_TASK

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_task_details
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            interactor.getTask(id,getTaskCallback())

        } catch (e: NoSuchElementException) {
            context?.displayToast(getString(R.string.noTaskFound))
        }
    }

    private fun getTaskCallback(): Callback<BackendTask> = object : Callback<BackendTask> {
        override fun onFailure(call: Call<BackendTask>, t: Throwable) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onResponse(call: Call<BackendTask>, response: Response<BackendTask>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse(response)
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleSomethingWentWrong() = instance.displayToast("Something went wrong!")

    private fun handleOkResponse(response: Response<BackendTask>) {
        response.body()?.run {
            detailsTaskTitle.text=title
            detailsTaskDescription.text=content
            detailsPriorityView.setBackgroundResource(when(taskPriority){
                1 -> PriorityColor.LOW.getColor()
                2-> PriorityColor.MEDIUM.getColor()
                else -> PriorityColor.HIGH.getColor()
            })
        }

    }

    override fun onTaskUpdated(task: BackendTask) {
        tryDisplayTask(taskID)
    }

    private fun displayTask(task: BackendTask) {
        detailsTaskTitle.text = task.title
        detailsTaskDescription.text = task.content
        detailsPriorityView.setBackgroundResource(
            when(task.taskPriority){
            1 -> PriorityColor.LOW.getColor()
            2-> PriorityColor.MEDIUM.getColor()
            else -> PriorityColor.HIGH.getColor()
        })
    }


    companion object {
        const val NO_TASK = ""

        fun newInstance(taskId: String): TaskDetailsFragment {
            val bundle = Bundle().apply { putString(EXTRA_TASK_ID, taskId) }
            return TaskDetailsFragment().apply { arguments = bundle }
        }
    }
}
