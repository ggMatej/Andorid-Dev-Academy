package hr.ferit.matejmijic.ada_taskie.ui.activities

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.common.showFragment
import hr.ferit.matejmijic.ada_taskie.model.Task
import hr.ferit.matejmijic.ada_taskie.ui.activities.base.BaseActivity
import hr.ferit.matejmijic.ada_taskie.ui.fragments.TaskDetailsFragment
import hr.ferit.matejmijic.ada_taskie.ui.fragments.TasksFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() {

    override fun getLayoutResourceId(): Int = R.layout.activity_main

    override fun setUpUi() {
        showFragment(TasksFragment.newInstance())
    }
}