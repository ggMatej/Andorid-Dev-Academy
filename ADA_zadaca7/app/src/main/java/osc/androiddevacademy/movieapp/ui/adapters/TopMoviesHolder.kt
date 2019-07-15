package osc.androiddevacademy.movieapp.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import osc.androiddevacademy.movieapp.common.loadImage
import osc.androiddevacademy.movieapp.model.Movie

class TopMoviesHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindItem(movie: Movie) {
        movieImage.loadImage(movie.poster)
    }
}