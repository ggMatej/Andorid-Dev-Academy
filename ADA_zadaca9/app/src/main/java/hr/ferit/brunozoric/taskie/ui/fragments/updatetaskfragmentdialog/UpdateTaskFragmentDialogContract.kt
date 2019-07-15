package hr.ferit.brunozoric.taskie.ui.fragments.updatetaskfragmentdialog

import hr.ferit.brunozoric.taskie.data.BackendTask

interface UpdateTaskFragmentDialogContract {

    interface View{

        fun taskUpdated(task: BackendTask)

        fun taskEditFail(error: String)

        fun onTaskReceived(task: BackendTask)

        fun onTaskFailed(error: String)
    }

    interface Presenter{

        fun setView(view: View)

        fun updateTask(id: String, title: String, content: String, taskPriority: Int)

        fun getTask(id: String)
    }
}