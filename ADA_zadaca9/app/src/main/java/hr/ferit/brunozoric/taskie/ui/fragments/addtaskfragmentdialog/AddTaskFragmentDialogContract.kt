package hr.ferit.brunozoric.taskie.ui.fragments.addtaskfragmentdialog

import hr.ferit.brunozoric.taskie.data.BackendTask

interface AddTaskFragmentDialogContract {

    interface View{

        fun onTaskAdded(task :BackendTask)

        fun onAddingTaskFailed(error: String)

    }

    interface Presenter{

        fun setView(view: View)

        fun addTask(title: String,content: String,taskPriority: Int)
    }
}

