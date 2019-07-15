package hr.ferit.matejmijic.ada_taskie.ui.activities

import androidx.fragment.app.Fragment
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.common.EXTRA_SCREEN_TYPE
import hr.ferit.matejmijic.ada_taskie.common.EXTRA_TASK_ID
import hr.ferit.matejmijic.ada_taskie.common.invisible
import hr.ferit.matejmijic.ada_taskie.ui.activities.base.BaseActivity
import hr.ferit.matejmijic.ada_taskie.ui.fragments.TaskDetailsFragment
import hr.ferit.matejmijic.ada_taskie.ui.fragments.TasksFragment
import kotlinx.android.synthetic.main.activity_main.*

class ContainerActivity: BaseActivity(){
    override fun getLayoutResourceId(): Int = R.layout.activity_main

    override fun setUpUi() {
        bottom_navigation.invisible()
        val screenType = intent.getStringExtra(EXTRA_SCREEN_TYPE)
        val id = intent.getIntExtra(EXTRA_TASK_ID,-1)
        if(screenType.isNotEmpty()){
            when(screenType){
                SCREEN_TASK_DETAILS -> showFragment(TaskDetailsFragment.newInstance(id))
            }
        } else {
            finish()
        }

    }

    companion object {
        const val SCREEN_TASK_DETAILS = "task_details"
    }
}