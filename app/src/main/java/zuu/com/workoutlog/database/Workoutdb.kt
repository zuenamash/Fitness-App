package zuu.com.workoutlog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import zuu.com.workoutlog.models.ExerciseCategory

@Database(entities = arrayOf(ExerciseCategory::class), version = 1)
abstract class Workoutdb: RoomDatabase() {
    abstract fun ExerciseCategoryDao(): ExerciseCategoryDao

    companion object {
        private var database:Workoutdb? = null

        fun getDatabase(context: Context): Workoutdb {
            if (database == null) {
                database = Room
                    .databaseBuilder(context, Workoutdb::class.java, "Workoutdb")
                    .fallbackToDestructiveMigration().build()
            }
            return database as Workoutdb
        }


}}