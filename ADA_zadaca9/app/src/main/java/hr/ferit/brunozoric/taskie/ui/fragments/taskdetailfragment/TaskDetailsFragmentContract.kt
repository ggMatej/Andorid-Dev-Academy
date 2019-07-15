package hr.ferit.brunozoric.taskie.ui.fragments.taskdetailfragment

import hr.ferit.brunozoric.taskie.data.BackendTask

interface TaskDetailsFragmentContract {

    interface View{
        fun onTaskReceived(task: BackendTask)

        fun onTaskFailed(error: String)
    }

    interface Presenter{
        fun setView(view: View)

        fun getTask(id: String)
    }
}