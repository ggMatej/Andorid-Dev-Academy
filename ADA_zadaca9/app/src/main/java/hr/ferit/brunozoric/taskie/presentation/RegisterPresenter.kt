package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.data.request.UserDataRequest
import hr.ferit.brunozoric.taskie.data.response.RegisterResponse
import hr.ferit.brunozoric.taskie.domain.domain.register.RegisterUseCase
import hr.ferit.brunozoric.taskie.ui.activities.register.RegisterContract

class RegisterPresenter(private val registerUseCase: RegisterUseCase): RegisterContract.Presenter {
    private lateinit var view: RegisterContract.View
    override fun setView(view: RegisterContract.View) {
        this.view = view
    }

    override fun register(name: String, email: String, password: String) {
        registerUseCase.execute(UserDataRequest(email,password,name),::onRegisterSuccess,::onRegisterError)
    }

    private fun onRegisterError(throwable: Throwable) =
        view.registrationFailed(throwable.localizedMessage)



    private fun onRegisterSuccess(response: RegisterResponse) =
        view.registrationSuccess(response)

}