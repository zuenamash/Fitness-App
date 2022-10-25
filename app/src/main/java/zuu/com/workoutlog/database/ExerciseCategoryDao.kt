package zuu.com.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import zuu.com.workoutlog.models.ExerciseCategory


@Dao
interface ExerciseCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExerciseCategory(ExerciseCategory: ExerciseCategory)

    fun getExerciseCategories(): LiveData<List<ExerciseCategory>>
}