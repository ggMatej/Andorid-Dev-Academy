package hr.ferit.matejmijic.ada_zadaca3_bmicalculator.ui.common

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getResourceViewId())
        setUpUi()
    }

    abstract fun setUpUi()

    abstract fun getResourceViewId(): Int


}
