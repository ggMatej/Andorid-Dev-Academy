package osc.androiddevacademy.movieapp.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_pager.*
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.ui.adapters.MoviePagerAdapter

class MoviesPagerFragment : Fragment() {

    companion object {
        private const val PAGER_LIST_EXTRA = "list_extra"
        private const val PAGER_SELECTED_MOVIE_EXTRA = "selected_movie"
        private const val PAGER_MOVIE_POSITION = "selected_movie_position"


        fun getInstance(movieList: ArrayList<Movie>, selectedMovie: Movie): MoviesPagerFragment {
            val pagerFragment = MoviesPagerFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(PAGER_LIST_EXTRA, movieList)
            bundle.putParcelable(PAGER_SELECTED_MOVIE_EXTRA, selectedMovie)
            bundle.putInt(PAGER_MOVIE_POSITION, movieList.indexOf(selectedMovie))

            pagerFragment.arguments = bundle
            return pagerFragment
        }
    }

    private val movieList = mutableListOf<Movie>()
    private val pagerAdapter by lazy {
        MoviePagerAdapter(
            childFragmentManager
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        movieList.addAll(arguments?.getParcelableArrayList(PAGER_LIST_EXTRA)!!)

        moviePager.adapter = pagerAdapter
        pagerAdapter.setMovies(movieList)
        moviePager.currentItem = arguments!!.getInt(PAGER_MOVIE_POSITION)

    }

}