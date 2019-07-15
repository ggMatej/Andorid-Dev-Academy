package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.topmovies.TopMoviesContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopMoviesFragmentPresenter(private val interactor: MovieInteractor): TopMoviesContract.Presenter {

    private lateinit var view: TopMoviesContract.View

    override fun setView(view: TopMoviesContract.View) {
        this.view = view
    }

    override fun getTopMovies() {
        interactor.getTopMovies(topMoviesCallback())
    }

    private fun topMoviesCallback(): Callback<MoviesResponse> = object : Callback<MoviesResponse>{
        override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
            view.onGetMoviesFailed()
        }

        override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
            response.body()?.movies?.run {
                view.onMoviesReceived(this)
            }
        }

    }


}