package hr.ferit.matejmijic.ada_taskie.ui.activities.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.common.showFragment
import hr.ferit.matejmijic.ada_taskie.ui.fragments.TasksFragment
import hr.ferit.matejmijic.ada_taskie.ui.fragments.viewPagerFragments.ViewPagerFragment
import kotlinx.android.synthetic.main.activity_main.*

//Sve jasno
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        setUpNavigationListener()
        setUpUi()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }



    private fun setUpNavigationListener() {
        bottom_navigation.setOnNavigationItemSelectedListener{
            lateinit var selectedFragment: Fragment
            when(it.itemId){
                R.id.nav_list -> selectedFragment = TasksFragment.newInstance()
                R.id.nav_about -> selectedFragment = ViewPagerFragment.newInstance()
            }

            showFragment(fragment = selectedFragment)
            return@setOnNavigationItemSelectedListener true
        }

    }

    protected fun showFragment(fragment: Fragment){
        showFragment(R.id.fragmentContainer,fragment)
    }

    abstract fun getLayoutResourceId(): Int

    abstract fun setUpUi()

}