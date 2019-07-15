package osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.favoritemovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_favourite.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.presentation.FavouriteMoviesFragmentPresenter
import osc.androiddevacademy.movieapp.ui.adapters.FavouriteMoviesAdapter
import osc.androiddevacademy.movieapp.ui.base.BaseFragment

class FavouriteMoviesFragment: BaseFragment(), FavouriteMoviesContract.View {

    private val SPAN_COUNT = 2


    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_favourite

    private val presenter: FavouriteMoviesContract.Presenter by lazy {
        FavouriteMoviesFragmentPresenter(MoviesDatabase.getInstance(App.getAppContext()))
    }

    private  val adapterTop: FavouriteMoviesAdapter by lazy {
        FavouriteMoviesAdapter()
    }

   private val movieList = arrayListOf<Movie>()


    override fun onMoviesReceived(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        adapterTop.setMovies(movies)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        presenter.getFavouriteMovies()
        favouriteMoviesGrid.apply {
            adapter = adapterTop
            layoutManager = GridLayoutManager(context, SPAN_COUNT)

        }

    }


    companion object {
        fun newInstance(): Fragment {
            return FavouriteMoviesFragment()
        }
    }
}