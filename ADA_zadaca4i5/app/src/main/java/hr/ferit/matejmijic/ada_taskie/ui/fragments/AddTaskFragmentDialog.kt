package hr.ferit.matejmijic.ada_taskie.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.common.displayToast
import hr.ferit.matejmijic.ada_taskie.model.Priority
import hr.ferit.matejmijic.ada_taskie.model.Task
import hr.ferit.matejmijic.ada_taskie.presistence.Repository
import hr.ferit.matejmijic.ada_taskie.presistence.TaskRoomRepository
import hr.ferit.matejmijic.ada_taskie.presistence.TaskiePrefs
import hr.ferit.matejmijic.ada_taskie.presistence.TasksRepository
import kotlinx.android.synthetic.main.fragment_dialog_new_task.*

class AddTaskFragmentDialog: DialogFragment() {


    private val repository: TasksRepository = TaskRoomRepository()

    private var taskAddedListener: TaskAddedListener? = null

    interface TaskAddedListener{
        fun onTaskAdded(task: Task)
    }

    fun setTaskAddedListener(listener: TaskAddedListener){
        taskAddedListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_new_task,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun initUi() {
        context?.let {
            prioritySelector.adapter = ArrayAdapter<Priority>(it, android.R.layout.simple_spinner_dropdown_item,Priority.values())
            prioritySelector.setSelection(TaskiePrefs.getInt(TaskiePrefs.KEY_LAST_PRIORITY))
        }

    }

    private fun initListeners() {
        saveTaskAction.setOnClickListener { saveTask() }
    }

    private fun saveTask() {
        if(isInputEmpty()){
            context?.displayToast(R.string.emptyFields)
            return
        }


        val title = newTaskTitleInput.text.toString()
        val description = newTaskDescriptionInput.text.toString()
        val priority = prioritySelector.selectedItem as Priority
        val task = Task(title = title, description = description, priority = priority)

        repository.addTask(task)

        when(priority){
            Priority.LOW -> TaskiePrefs.store(TaskiePrefs.KEY_LAST_PRIORITY, 0)
            Priority.MEDIUM -> TaskiePrefs.store(TaskiePrefs.KEY_LAST_PRIORITY, 1)
            Priority.HIGH -> TaskiePrefs.store(TaskiePrefs.KEY_LAST_PRIORITY,2)

        }

        clearUi()

        taskAddedListener?.onTaskAdded(task)
        dismiss()
    }

    private fun clearUi() {
        newTaskTitleInput.text.clear()
        newTaskDescriptionInput.text.clear()
        //prioritySelector.setSelection(1)
    }

    private fun isInputEmpty(): Boolean = TextUtils.isEmpty(newTaskTitleInput.text) || TextUtils.isEmpty(newTaskDescriptionInput.text)

    companion object {
        fun newInstance(): AddTaskFragmentDialog = AddTaskFragmentDialog()
    }

}