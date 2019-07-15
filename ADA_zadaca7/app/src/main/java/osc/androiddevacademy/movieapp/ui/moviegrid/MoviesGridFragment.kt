package osc.androiddevacademy.movieapp.ui.moviegrid

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragemnt_movie_grid.*
import kotlinx.android.synthetic.main.item_movie.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.displayToast
import osc.androiddevacademy.movieapp.common.showFragment
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.presentation.FavouriteMoviesFragmentPresenter
import osc.androiddevacademy.movieapp.presentation.MoviesGridFragmentPresenter
import osc.androiddevacademy.movieapp.ui.adapters.FavouriteMoviesAdapter
import osc.androiddevacademy.movieapp.ui.adapters.MoviesGridAdapter
import osc.androiddevacademy.movieapp.ui.base.BaseFragment
import osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.favoritemovies.FavouriteMoviesContract
import osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.favoritemovies.FavouriteMoviesFragment
import osc.androiddevacademy.movieapp.ui.moviedetails.MoviesPagerFragment

class MoviesGridFragment : BaseFragment(), MoviesGridFragmentContract.View {
    private val SPAN_COUNT = 2

    private val presenter: MoviesGridFragmentContract.Presenter by lazy {
        MoviesGridFragmentPresenter(BackendFactory.getMovieInteractor())

    }

    private val gridAdapter by lazy {
        MoviesGridAdapter(
            { onMovieClicked(it) },
            { onFavoriteClicked(it) })
    }




    private val movieList = arrayListOf<Movie>()
    private val favouriteMovieList: MutableList<Movie> = mutableListOf()




    override fun onFavouriteMoviesReceived(movies: List<Movie>) {
        favouriteMovieList.clear()
        favouriteMovieList.addAll(movies)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragemnt_movie_grid

    override fun onGetMoviesFailed() {
        activity?.displayToast("Failed")
    }

    override fun onMoviesReceived(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        for(movie in movies){
            if(favouriteMovieList.contains(movie)){
                movie.isFavorite = true
            }
        }
        gridAdapter.setMovies(movieList)
        gridAdapter.notifyDataSetChanged()
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)

        moviesGrid.apply {
            adapter = gridAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
        }

        requestPopularMovies()
        getFavouriteMovies()
    }

    private fun getFavouriteMovies() {
        presenter.getFavouriteMovies()
    }

    override fun onResume() {
        super.onResume()


    }

    private fun requestPopularMovies() {
        presenter.getPopularMovies()
    }



    private fun onMovieClicked(movie: Movie) {
        activity?.showFragment(
            R.id.mainFragmentHolder,
            MoviesPagerFragment.getInstance(movieList, movie),
            true
        )
    }

    private fun onFavoriteClicked(movie: Movie) {
        when(movie.isFavorite){
            true -> {
                presenter.deleteFavourite(movie)
                movieFavorite.setImageResource(R.drawable.ic_favorite_empty)
                gridAdapter.notifyDataSetChanged()
            }
            else -> {
                presenter.addFavourite(movie)
                movieFavorite.setImageResource(R.drawable.ic_favorite_full)
                gridAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return MoviesGridFragment()
        }
    }

}