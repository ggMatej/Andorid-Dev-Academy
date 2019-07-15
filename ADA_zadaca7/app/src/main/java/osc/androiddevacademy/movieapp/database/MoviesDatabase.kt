package osc.androiddevacademy.movieapp.database

import android.content.Context
import androidx.room.*
import osc.androiddevacademy.movieapp.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        private var instance: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, MoviesDatabase::class.java, "MoviesDatabase")
                        .allowMainThreadQueries().build()
            }
            return instance as MoviesDatabase
        }


    }

}