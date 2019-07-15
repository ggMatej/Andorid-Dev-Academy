package osc.androiddevacademy.movieapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import osc.androiddevacademy.movieapp.model.Movie

@Dao
interface MoviesDao {

    @Insert(onConflict = REPLACE)
    fun addFavoriteMovie(movie: Movie)

    @Delete
    fun deleteFavoriteMovie(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getFavoriteMovies(): List<Movie>





}