package hr.ferit.brunozoric.taskie.ui.activities.register

import android.content.Intent
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.common.onClick
import hr.ferit.brunozoric.taskie.data.response.RegisterResponse
import hr.ferit.brunozoric.taskie.ui.activities.base.BaseActivity
import hr.ferit.brunozoric.taskie.ui.activities.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.ext.android.inject

class RegisterActivity : BaseActivity(), RegisterContract.View {
    private val presenter by inject<RegisterContract.Presenter>()

    override fun getLayoutResourceId(): Int = R.layout.activity_register

    override fun setUpUi() {
        presenter.setView(this)
        register.onClick { registerClicked() }
        goToLogin.onClick { goToLoginClicked() }
    }

    private fun registerClicked() {
        presenter.register(name.text.toString(),email.text.toString(), password.text.toString() )

    }

    private fun goToLoginClicked() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun registrationSuccess(response: RegisterResponse) {
        this.displayToast("Successfully registered")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun registrationFailed(error: String) {
        this.displayToast(error)
    }
}