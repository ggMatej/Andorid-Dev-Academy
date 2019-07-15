package hr.ferit.matejmijic.ada_taskie.ui.fragments.viewPagerFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.ui.adapters.TaskPagerAdapter
import hr.ferit.matejmijic.ada_taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_view_pager.*



class ViewPagerFragment: BaseFragment() {



    override fun getLayoutResourceId(): Int = R.layout.fragment_view_pager



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        viewPager.adapter = TaskPagerAdapter(childFragmentManager, context!!)
        tabs.setupWithViewPager(viewPager)
    }

    companion object {
        fun newInstance(): Fragment {
            return ViewPagerFragment()
        }
    }
}