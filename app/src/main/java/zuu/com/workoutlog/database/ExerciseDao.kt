package zuu.com.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import zuu.com.workoutlog.models.Exercises

@Dao

interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercises)


    @Query("SELECT*FROM")
    fun getExercises():LiveData<List<Exercises>>
}