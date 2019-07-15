package osc.androiddevacademy.movieapp.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.loadImage
import osc.androiddevacademy.movieapp.model.Movie

class MoviesGridViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindItem(movie: Movie, onMovieClickListener: (Movie) -> Unit, onFavoriteClickListener: (Movie) -> Unit) {
        movieImage.loadImage(movie.poster)
        movieFavorite.setImageResource(if (movie.isFavorite) R.drawable.ic_favorite_full else R.drawable.ic_favorite_empty)

        containerView.setOnClickListener {
            onMovieClickListener(movie)
        }

        movieFavorite.setOnClickListener {
            onFavoriteClickListener(movie)
        }
    }

}