package osc.androiddevacademy.movieapp.networking.interactors

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.model.ReviewsResponse
import osc.androiddevacademy.movieapp.networking.MovieApiService
import retrofit2.Callback

class MovieInteractorImpl(private val apiService: MovieApiService): MovieInteractor {

    override fun getPopularMovies(popularMoviesCallback: Callback<MoviesResponse>) {
        apiService.getPopularMovies().enqueue(popularMoviesCallback)
    }

    override fun getTopMovies(topMoviesCallback: Callback<MoviesResponse>) {
        apiService.getTopMovies().enqueue(topMoviesCallback)
    }

    override fun getMovie(movieId: Int, movieCallback: Callback<Movie>) {
        apiService.getMovie(movieId).enqueue(movieCallback)
    }

    override fun getReviewsForMovie(movieId: Int, callback: Callback<ReviewsResponse>) {
        apiService.getReviews(movieId).enqueue(callback)
    }
}