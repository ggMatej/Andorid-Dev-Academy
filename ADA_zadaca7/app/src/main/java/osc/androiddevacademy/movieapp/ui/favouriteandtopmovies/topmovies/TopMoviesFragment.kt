package osc.androiddevacademy.movieapp.ui.favouriteandtopmovies.topmovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_top_movie.*
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.displayToast
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.presentation.TopMoviesFragmentPresenter
import osc.androiddevacademy.movieapp.ui.adapters.TopMoviesAdapter
import osc.androiddevacademy.movieapp.ui.base.BaseFragment

class TopMoviesFragment: BaseFragment(), TopMoviesContract.View {

    private val SPAN_COUNT = 2
    private val movieList = arrayListOf<Movie>()


    private val presenter: TopMoviesContract.Presenter by lazy {
        TopMoviesFragmentPresenter(BackendFactory.getMovieInteractor())
    }
    private  val adapterTop: TopMoviesAdapter by lazy {
        TopMoviesAdapter()
    }

    override fun onMoviesReceived(movies: ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        adapterTop.setMovies(movies)
    }

    override fun onGetMoviesFailed() {
        context?.displayToast("FAILED")
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_top_movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestTopMovies()
        presenter.setView(this)
        topMoviesGrid.apply {
            adapter = adapterTop
            layoutManager = GridLayoutManager(context, SPAN_COUNT)

        }

    }

    private fun requestTopMovies() {
        presenter.getTopMovies()
    }

    companion object {
        fun newInstance(): Fragment {
            return TopMoviesFragment()
        }
    }
}