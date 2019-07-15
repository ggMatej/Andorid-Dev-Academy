package hr.ferit.brunozoric.taskie.ui.activities.login

import hr.ferit.brunozoric.taskie.data.response.LoginResponse

interface LoginContract {

    interface View {
        fun loginSuccessfull(response: LoginResponse)

        fun loginFailed(error: String)
    }

    interface Presenter{
        fun setView(view: View)

        fun login(email: String, password: String)
    }
}