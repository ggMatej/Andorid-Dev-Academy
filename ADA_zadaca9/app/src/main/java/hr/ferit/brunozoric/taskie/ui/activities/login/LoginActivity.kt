package hr.ferit.brunozoric.taskie.ui.activities.login

import android.content.Intent
import android.util.Log
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.data.response.LoginResponse
import hr.ferit.brunozoric.taskie.prefs.provideSharedPrefs
import hr.ferit.brunozoric.taskie.ui.activities.MainActivity
import hr.ferit.brunozoric.taskie.ui.activities.register.RegisterActivity
import hr.ferit.brunozoric.taskie.ui.activities.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import kotlin.math.log

class LoginActivity : BaseActivity(), LoginContract.View {
    private val presenter by inject<LoginContract.Presenter>()
    private val prefs = provideSharedPrefs()


    override fun getLayoutResourceId(): Int = R.layout.activity_login

    override fun setUpUi() {
        presenter.setView(this)

        login.setOnClickListener { signInClicked() }
        goToLogin.setOnClickListener { goToRegistrationClicked() }
    }

    private fun signInClicked() {
        presenter.login(email.text.toString(), password.text.toString())
    }



    private fun goToRegistrationClicked() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }



    override fun loginSuccessfull(response: LoginResponse) {
        this.displayToast("Successfully logged in!")
        response.token?.let { prefs.storeUserToken(it) }
        Log.d("tag",prefs.getUserToken())
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun loginFailed(error: String) = applicationContext.displayToast(error)


}