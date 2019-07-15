package osc.androiddevacademy.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_movies.*
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.showFragment
import osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.FavouriteAndTopPagerFragment
import osc.androiddevacademy.movieapp.ui.moviegrid.MoviesGridFragment

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        setUpNavigationListener()
        initMoviesGridFragment()
    }



    private fun setUpNavigationListener() {
        bottom_navigation.setOnNavigationItemSelectedListener{
            lateinit var selectedFragment: Fragment
            when(it.itemId){
                R.id.nav_popular -> selectedFragment = MoviesGridFragment.newInstance()
                R.id.nav_top -> selectedFragment = FavouriteAndTopPagerFragment.newInstance()
            }

            showFragment(fragment = selectedFragment)
            return@setOnNavigationItemSelectedListener true
        }

    }

    protected fun showFragment(fragment: Fragment){
        showFragment(R.id.mainFragmentHolder,fragment)
    }


    private fun initMoviesGridFragment(){
        showFragment(R.id.mainFragmentHolder,
            MoviesGridFragment()
        )
    }
}
