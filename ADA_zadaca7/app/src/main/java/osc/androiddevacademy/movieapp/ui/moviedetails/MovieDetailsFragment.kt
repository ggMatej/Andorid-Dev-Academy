package osc.androiddevacademy.movieapp.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragemnt_details.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.loadImage
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.Review
import osc.androiddevacademy.movieapp.model.ReviewsResponse
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.presentation.MovieDetailsFragmentPresenter
import osc.androiddevacademy.movieapp.ui.adapters.ReviewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsFragment : Fragment(), MovieDetailsFragmentContract.View {

    private val reviewsAdapter by lazy { ReviewAdapter() }

    private val appDatabase by lazy { MoviesDatabase.getInstance(App.getAppContext()) }

    private val presenter: MovieDetailsFragmentContract.Presenter by lazy {
        MovieDetailsFragmentPresenter(BackendFactory.getMovieInteractor())
    }

    companion object {
        private const val MOVIE_EXTRA = "movie_extra"
        fun getInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_EXTRA, movie)
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(taskId: Int): MovieDetailsFragment {
            val bundle = Bundle().apply { putInt(MOVIE_EXTRA,taskId) }
            return MovieDetailsFragment().apply { arguments = bundle }
        }
    }

    private lateinit var movie: Movie

    override fun onReviewsReceived(review: List<Review>) {
        reviewsAdapter.setReviewList(review)
    }

    override fun onGetReviewsFailed(t: Throwable) {
        t.printStackTrace()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragemnt_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = arguments?.getParcelable(MOVIE_EXTRA) as Movie
        presenter.setView(this)
        presenter.setMovie(movie)
        initUi()
        getReviews()
        initListeners()
    }

    private fun initUi(){
        movieImage.loadImage(movie.poster)
        movieTitle.text = movie.title
        movieOverview.text = movie.overview
        movieReleaseDate.text = movie.releaseDate
        movieVoteAverage.text = movie.averageVote.toString()

        movieReviewList.apply {
            adapter = reviewsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initListeners(){
        movieFavorite.setOnClickListener {
            onFavoriteClicked()
        }
    }

    private fun onFavoriteClicked(){

    }

    private fun getReviews(){
        presenter.getReviews()
    }




}