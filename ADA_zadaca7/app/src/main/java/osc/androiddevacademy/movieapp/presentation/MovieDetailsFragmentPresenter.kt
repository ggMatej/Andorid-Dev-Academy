package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.ReviewsResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.moviedetails.MovieDetailsFragmentContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsFragmentPresenter(private val interactor: MovieInteractor): MovieDetailsFragmentContract.Presenter {


    private lateinit var view: MovieDetailsFragmentContract.View
    private lateinit var movie: Movie

    override fun setMovie(movie: Movie) {
        this.movie = movie
    }


    override fun setView(view: MovieDetailsFragmentContract.View) {
        this.view = view
    }

    override fun getReviews() {
        interactor.getReviewsForMovie(movie.id,reviewsCallback())
    }

    private fun reviewsCallback(): Callback<ReviewsResponse> = object : Callback<ReviewsResponse>{
        override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
                    view.onGetReviewsFailed(t)
        }

        override fun onResponse(call: Call<ReviewsResponse>, response: Response<ReviewsResponse>) {
            if (response.isSuccessful){
                response.body()?.reviews?.run {
                    view.onReviewsReceived(this)
                }
            }
        }
    }

}
