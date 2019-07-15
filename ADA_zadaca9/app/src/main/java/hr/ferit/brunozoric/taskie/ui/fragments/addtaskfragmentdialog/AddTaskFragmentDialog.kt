package hr.ferit.brunozoric.taskie.ui.fragments.addtaskfragmentdialog

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.common.priorityFactory
import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.PriorityColor
import kotlinx.android.synthetic.main.fragment_dialog_new_task.*
import org.koin.android.ext.android.inject

class AddTaskFragmentDialog : DialogFragment(), AddTaskFragmentDialogContract.View {

    private val presenter by inject<AddTaskFragmentDialogContract.Presenter>()
    private var taskAddedListener: TaskAddedListener? = null

    companion object {
        fun newInstance(): AddTaskFragmentDialog {
            return AddTaskFragmentDialog()
        }
    }

    interface TaskAddedListener {
        fun onTaskAdded(task: BackendTask)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
    }

    fun setTaskAddedListener(listener: TaskAddedListener) {
        taskAddedListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_new_task, container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.setView(this)
        super.onViewCreated(view, savedInstanceState)
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

    private fun initListeners() = saveTaskAction.setOnClickListener { saveTask() }

    private fun saveTask() {
        if (isInputEmpty()) {
            context?.displayToast(getString(R.string.emptyFields))
            return
        }

        val title = newTaskTitleInput.text.toString()
        val description = newTaskDescriptionInput.text.toString()
        val priority = prioritySelector.priorityFactory()
        presenter.addTask(title, description, priority.getValue())
        clearUi()
    }

    private fun clearUi() {
        newTaskTitleInput.text.clear()
        newTaskDescriptionInput.text.clear()
        prioritySelector.setSelection(0)
    }

    private fun isInputEmpty(): Boolean = isEmpty(newTaskTitleInput.text) || isEmpty(newTaskDescriptionInput.text)

    private fun onTaskiesReceived(task: BackendTask) {
        taskAddedListener?.onTaskAdded(task)
        dismiss()
    }

    override fun onTaskAdded(task: BackendTask) {
        task?.run { onTaskiesReceived(this) }
    }

    override fun onAddingTaskFailed(error: String) {
        this.activity?.displayToast("Something went wrong!")
    }
}