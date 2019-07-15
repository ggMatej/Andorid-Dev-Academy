package osc.androiddevacademy.movieapp.ui.moviedetails

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.Review
import osc.androiddevacademy.movieapp.ui.base.BasePresenter

interface MovieDetailsFragmentContract {

    interface View{

        fun onReviewsReceived(review: List<Review>)

        fun onGetReviewsFailed(t: Throwable)
    }

    interface Presenter: BasePresenter<View>{

        fun getReviews()

        fun setMovie(movie: Movie)
    }
}