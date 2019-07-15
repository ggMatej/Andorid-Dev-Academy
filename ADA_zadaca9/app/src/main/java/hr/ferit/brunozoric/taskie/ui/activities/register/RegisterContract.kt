package hr.ferit.brunozoric.taskie.ui.activities.register

import hr.ferit.brunozoric.taskie.data.response.RegisterResponse

interface RegisterContract {
    interface View{
        fun registrationSuccess(response: RegisterResponse)

        fun registrationFailed(error: String)
    }

    interface Presenter{
        fun setView(view: View)

        fun register(name: String, email: String, password: String)
    }
}