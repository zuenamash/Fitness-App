package zuu.com.workoutlog.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zuu.com.workoutlog.models.ExerciseCategory
import zuu.com.workoutlog.models.Exercises
import zuu.com.workoutlog.repository.ExerciseRepository


class ExerciseViewModel: ViewModel() {
    val exerciseRepository = ExerciseRepository()
    val exerciseCategoryLiveData = MutableLiveData<List<ExerciseCategory>>()
    val exerciseLiveData = MutableLiveData<List<Exercises>>()

    val errorLiveData = MutableLiveData<String?>()


    fun fetchExerciseCategories(accessToken: String) {
        viewModelScope.launch {
            val response = exerciseRepository.fetchExerciseCategory(accessToken)
            if (response.isSuccessful) {
                exerciseCategoryLiveData.postValue(response.body())

            } else {
                val errorMsg = response.errorBody()?.string()
                errorLiveData.postValue(errorMsg)
            }
        }

    }

    fun fetchExercises(accessToken: String) {
        viewModelScope.launch {
            val response = exerciseRepository.fetchExercises(accessToken)
            if (response.isSuccessful) {
                exerciseLiveData.postValue(response.body())
            } else {
                val errorMsg = response.errorBody()?.string()
                errorLiveData.postValue(errorMsg)
            }
        }
    }

    fun fetchDbCategories(){
        exerciseCategoryLiveData = exerciseRepository.getDbCategories()
    }

    fun fetchDbExercises() {
        exerciseCategoryLiveData = exerciseRepository.getExercises()
    }
}
