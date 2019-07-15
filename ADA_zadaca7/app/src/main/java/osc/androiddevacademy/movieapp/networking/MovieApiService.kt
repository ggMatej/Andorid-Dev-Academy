package osc.androiddevacademy.movieapp.networking

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.model.ReviewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("popular")
    fun getPopularMovies(): Call<MoviesResponse>

    @GET("top_rated")
    fun getTopMovies(): Call<MoviesResponse>

    @GET("{id}")
    fun getMovie(@Path(value = "id") movieId: Int): Call<Movie>

    @GET("{id}/reviews")
    fun getReviews(@Path(value = "id") movieId: Int) : Call<ReviewsResponse>

}