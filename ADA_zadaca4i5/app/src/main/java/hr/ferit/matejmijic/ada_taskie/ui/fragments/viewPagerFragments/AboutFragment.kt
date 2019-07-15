package hr.ferit.matejmijic.ada_taskie.ui.fragments.viewPagerFragments

import androidx.fragment.app.Fragment
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.ui.fragments.base.BaseFragment

class AboutFragment: BaseFragment() {
    override fun getLayoutResourceId(): Int = R.layout.fragment_about

    companion object {
        fun newInstance(): Fragment {
            return AboutFragment()
        }
    }
}