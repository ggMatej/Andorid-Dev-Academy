package osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.favoritemovies

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.ui.base.BasePresenter

interface FavouriteMoviesContract {
    interface View{

        fun onMoviesReceived(movies: List<Movie>)


    }

    interface Presenter: BasePresenter<View> {

        fun getFavouriteMovies()

    }
}