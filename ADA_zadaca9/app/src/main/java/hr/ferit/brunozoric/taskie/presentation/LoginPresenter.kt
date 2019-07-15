package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.common.isValidEmail
import hr.ferit.brunozoric.taskie.common.isValidPassword
import hr.ferit.brunozoric.taskie.data.request.UserDataRequest
import hr.ferit.brunozoric.taskie.data.response.LoginResponse
import hr.ferit.brunozoric.taskie.domain.domain.login.LoginUseCase
import hr.ferit.brunozoric.taskie.ui.activities.login.LoginContract

class LoginPresenter(private val loginUseCase: LoginUseCase): LoginContract.Presenter {
    private lateinit var view: LoginContract.View

    override fun setView(view: LoginContract.View) {
        this.view = view
    }

    override fun login(email: String, password: String) {

        loginUseCase.execute(UserDataRequest(email,password), ::onLoginSucces, ::onLoginError)

    }

    private fun onLoginError(throwable: Throwable) =
        view.loginFailed(throwable.localizedMessage)

    private fun onLoginSucces(response: LoginResponse) =
        view.loginSuccessfull(response)


}