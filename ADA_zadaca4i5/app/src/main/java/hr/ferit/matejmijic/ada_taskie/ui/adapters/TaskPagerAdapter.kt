package hr.ferit.matejmijic.ada_taskie.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.ui.fragments.viewPagerFragments.AboutFragment
import hr.ferit.matejmijic.ada_taskie.ui.fragments.viewPagerFragments.ApplicationAuthorFragment
import hr.ferit.matejmijic.ada_taskie.ui.fragments.TasksFragment

class TaskPagerAdapter(fm: FragmentManager,context: Context): FragmentPagerAdapter(fm) {

    private var mContext: Context = context


    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> AboutFragment.newInstance()
            1 -> ApplicationAuthorFragment.newInstance()
            else -> TasksFragment.newInstance()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence{
        when(position){
            0 ->return  mContext.getString(R.string.tab_about)
            1 ->return  mContext.getString(R.string.tab_author)
        }
        return ""
    }
}