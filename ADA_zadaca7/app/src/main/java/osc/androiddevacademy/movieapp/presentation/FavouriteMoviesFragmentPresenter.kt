package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.favoritemovies.FavouriteMoviesContract

class FavouriteMoviesFragmentPresenter(private val appDatabase: MoviesDatabase ): FavouriteMoviesContract.Presenter {

    private lateinit var view: FavouriteMoviesContract.View


    override fun setView(view: FavouriteMoviesContract.View) {
        this.view = view
    }

    override fun getFavouriteMovies() {
        view.onMoviesReceived(appDatabase.moviesDao().getFavoriteMovies())
    }

}