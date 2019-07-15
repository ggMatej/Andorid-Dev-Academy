package hr.ferit.matejmijic.ada_taskie.ui.fragments.viewPagerFragments

import androidx.fragment.app.Fragment
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.ui.fragments.base.BaseFragment

class ApplicationAuthorFragment: BaseFragment() {
    override fun getLayoutResourceId(): Int = R.layout.fragment_application_author

    companion object {
        fun newInstance(): Fragment {
            return ApplicationAuthorFragment()
        }
    }
}