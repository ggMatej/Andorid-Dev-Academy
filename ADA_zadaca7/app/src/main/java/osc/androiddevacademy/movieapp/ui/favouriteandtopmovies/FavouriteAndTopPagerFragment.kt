package osc.androiddevacademy.movieapp.ui.favouriteandtopmovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_pager_favourite_top.*
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.ui.adapters.FavouriteAndTopMoviesPagerAdapter
import osc.androiddevacademy.movieapp.ui.base.BaseFragment

class FavouriteAndTopPagerFragment: BaseFragment() {
    override fun getLayoutResourceId(): Int = R.layout.fragment_pager_favourite_top

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        viewPager.adapter = FavouriteAndTopMoviesPagerAdapter(childFragmentManager, context!!)
        tabs.setupWithViewPager(viewPager)
    }

    companion object {
        fun newInstance(): Fragment {
            return FavouriteAndTopPagerFragment()
        }
    }
}