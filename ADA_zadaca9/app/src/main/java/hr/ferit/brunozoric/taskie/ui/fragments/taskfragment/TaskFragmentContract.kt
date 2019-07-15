package hr.ferit.brunozoric.taskie.ui.fragments.taskfragment

import hr.ferit.brunozoric.taskie.data.BackendTask
import hr.ferit.brunozoric.taskie.data.response.GetTasksResponse

interface TaskFragmentContract {

    interface View{

        fun onTasksReceived(response: GetTasksResponse)

        fun onFailure(error: String)
    }

    interface Presenter{

        fun setView(view: View)

        fun getTasks()

    }
}