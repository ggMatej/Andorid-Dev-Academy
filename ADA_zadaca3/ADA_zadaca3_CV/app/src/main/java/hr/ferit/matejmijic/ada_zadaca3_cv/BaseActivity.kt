package hr.ferit.matejmijic.ada_zadaca3_cv

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewResourceId())
        setUpUi()
    }

    abstract fun setUpUi()

    abstract fun getViewResourceId(): Int
}
