package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.moviegrid.MoviesGridFragmentContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesGridFragmentPresenter(private val interactor: MovieInteractor): MoviesGridFragmentContract.Presenter {
    private lateinit var view: MoviesGridFragmentContract.View

    private val appDatabase by lazy { MoviesDatabase.getInstance(App.getAppContext()) }


    override fun deleteFavourite(movie: Movie) {
        movie.isFavorite = false
        appDatabase.moviesDao().deleteFavoriteMovie(movie)


    }

    override fun addFavourite(movie: Movie) {
        movie.isFavorite = true
        appDatabase.moviesDao().addFavoriteMovie(movie)

    }


    override fun setView(view: MoviesGridFragmentContract.View) {
        this.view = view
    }

    override fun getFavouriteMovies() {
        view.onFavouriteMoviesReceived(appDatabase.moviesDao().getFavoriteMovies())
    }

    override fun getPopularMovies() {
        interactor.getPopularMovies(popularMoviesCallback())
    }

    private fun popularMoviesCallback(): Callback<MoviesResponse> =
        object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                view.onGetMoviesFailed()
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.movies?.run {
                        view.onMoviesReceived(this)
                    }
                }
            }
        }
}