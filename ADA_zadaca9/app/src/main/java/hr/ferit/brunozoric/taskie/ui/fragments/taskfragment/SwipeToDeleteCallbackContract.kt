package hr.ferit.brunozoric.taskie.ui.fragments.taskfragment

interface SwipeToDeleteCallbackContract {

    interface View{
        fun onTaskDelted()

        fun showError(error: String)
    }

    interface Presenter{
        fun setView(view: View)

        fun delteTask(id :String)
    }
}