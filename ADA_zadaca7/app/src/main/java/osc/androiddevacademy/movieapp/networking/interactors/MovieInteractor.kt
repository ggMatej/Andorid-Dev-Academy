package osc.androiddevacademy.movieapp.networking.interactors

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.model.ReviewsResponse
import retrofit2.Callback

interface MovieInteractor {

    fun getPopularMovies(popularMoviesCallback: Callback<MoviesResponse>)

    fun getTopMovies(topMoviesCallback: Callback<MoviesResponse>)

    fun getMovie(movieId: Int, movieCallback: Callback<Movie>)

    fun getReviewsForMovie(movieId: Int, callback: Callback<ReviewsResponse>)

}