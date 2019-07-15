package hr.ferit.brunozoric.taskie.ui.fragments.updatetaskfragmentdialog

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.data.PriorityColor
import hr.ferit.brunozoric.taskie.data.BackendTask
import kotlinx.android.synthetic.main.fragment_dialog_new_task.*
import org.koin.android.ext.android.inject

class UpdateTaskFragmentDialog: DialogFragment(), UpdateTaskFragmentDialogContract.View {

    private val presenter by inject<UpdateTaskFragmentDialogContract.Presenter>()

    private var taskUpdatedListener: TaskUpdatedListener? = null
    var taskId: String = ""
    lateinit var tasky: BackendTask

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
        presenter.setView(this)
        presenter.getTask(taskId)
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
        presenter.updateTask(taskId, title, description, priority)
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

    override fun taskUpdated(task: BackendTask) {
        task?.run {
            onTaskiesUpdated(this)
        }
    }

    override fun taskEditFail(error: String) {
        context?.displayToast(error)
    }

    override fun onTaskReceived(task: BackendTask) {
        task?.run {
            newTaskTitleInput.setText(this.title)
            newTaskDescriptionInput.setText(this.content)
            prioritySelector.setSelection(this.taskPriority-1)
            tasky = this
        }
    }

    override fun onTaskFailed(error: String) {
        context?.displayToast(error)
    }
}