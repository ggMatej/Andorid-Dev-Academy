package hr.ferit.matejmijic.ada_taskie.db

import android.content.Context
import androidx.room.*
import hr.ferit.matejmijic.ada_taskie.converters.Converters
import hr.ferit.matejmijic.ada_taskie.model.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(Converters::class)
abstract class DaoProvider: RoomDatabase() {

    abstract fun taskieDao(): TaskieDao

    companion object {
        private var instance: DaoProvider? = null

        fun getInstance(context: Context): DaoProvider {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DaoProvider::class.java,
                    "TaskDb"
                ).allowMainThreadQueries().build()
            }
            return instance as DaoProvider
        }
    }

}