package hr.ferit.brunozoric.taskie.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.Taskie
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.model.PriorityColor
import hr.ferit.brunozoric.taskie.common.RESPONSE_OK
import hr.ferit.brunozoric.taskie.common.priorityFactory
import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest
import hr.ferit.brunozoric.taskie.model.request.UpdateTaskRequest
import hr.ferit.brunozoric.taskie.model.response.GetTaskResponse
import hr.ferit.brunozoric.taskie.networking.BackendFactory
import kotlinx.android.synthetic.main.fragment_dialog_new_task.*
import kotlinx.android.synthetic.main.fragment_task_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateTaskFragmentDialog: DialogFragment() {


    private var taskUpdatedListener: TaskUpdatedListener? = null
    private val interactor = BackendFactory.getTaskieInteractor()
    var taskId: String = ""
    lateinit var task: BackendTask

    companion object {
        fun newInstance(): UpdateTaskFragmentDialog {
            return UpdateTaskFragmentDialog()
        }
    }

    interface TaskUpdatedListener {
        fun onTaskUpdated(task: BackendTask)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
    }

    fun setTaskUpdatedListener(listener: TaskUpdatedListener) {
        taskUpdatedListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_update_task, container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        interactor.getTask(taskId ,getTaskCallback())
        initUi()
        initListeners()
    }

    private fun initUi() {
        context?.let {
            prioritySelector.adapter =
                ArrayAdapter<PriorityColor>(it, android.R.layout.simple_spinner_dropdown_item, PriorityColor.values())
            prioritySelector.setSelection(0)
        }

    }

    private fun initListeners() = saveTaskAction.setOnClickListener { updateTask() }

    private fun updateTask() {
        if(isInputEmpty()){
            context?.displayToast(R.string.emptyFields)
            return
        }


        val title = newTaskTitleInput.text.toString()
        val description = newTaskDescriptionInput.text.toString()
        val priority = prioritySelector.selectedItemPosition+1
        clearUi()
        interactor.editNote(UpdateTaskRequest(taskId ,title, description, priority), updateTaskCallback())






    }

    private fun updateTaskCallback() = object:  Callback<BackendTask> {
        override fun onFailure(call: Call<BackendTask>, t: Throwable) {

        }

        override fun onResponse(call: Call<BackendTask>, response: Response<BackendTask>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse2(response)
                    else -> handleSomethingWentWrong2()
                }
            }
        }

    }

    private fun handleSomethingWentWrong2() {

    }

    private fun handleOkResponse2(response: Response<BackendTask>) {
        response.body()?.run {
            onTaskiesUpdated(this)
        }
    }

    private fun onTaskiesUpdated(backendTask: BackendTask){
        taskUpdatedListener?.onTaskUpdated(backendTask)
        dismiss()
    }


    private fun clearUi() {
        newTaskTitleInput.text.clear()
        newTaskDescriptionInput.text.clear()
        prioritySelector.setSelection(0)
    }
    private fun isInputEmpty(): Boolean = TextUtils.isEmpty(newTaskTitleInput.text) || TextUtils.isEmpty(
        newTaskDescriptionInput.text
    )





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

    private fun handleSomethingWentWrong() = Taskie.instance.displayToast("Something went wrong!")

    private fun handleOkResponse(response: Response<BackendTask>) {

        response.body()?.run {
            newTaskTitleInput.setText(this.title)
            newTaskDescriptionInput.setText(this.content)
            prioritySelector.setSelection(this.taskPriority-1)
            task = this

        }

    }
}