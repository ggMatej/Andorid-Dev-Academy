package osc.androiddevacademy.movieapp.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.favoritemovies.FavouriteMoviesFragment
import osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.topmovies.TopMoviesFragment

class FavouriteAndTopMoviesPagerAdapter(fm: FragmentManager, context: Context): FragmentPagerAdapter(fm) {

    private var mContext: Context = context

    override fun getItem(position: Int): Fragment {
        return when(position){
            1 -> FavouriteMoviesFragment.newInstance()
            else -> TopMoviesFragment.newInstance()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            1 -> return mContext.getString(R.string.tab_favourite)
            0 -> return mContext.getString(R.string.tab_top)
        }
        return ""
    }


}