package zuu.com.workoutlog.viewModel

import androidx.lifecycle.MutableLiveData
import zuu.com.workoutlog.models.ExerciseCategory
import zuu.com.workoutlog.repository.ExerciseRepository

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ExerciseViewModel {
    val exerciseRepository = ExerciseRepository()
    val exerciseCategoryLiveData = MutableLiveData<List<ExerciseCategory >>()
    val errorLiveData = MutableLiveData<String?>()


    suspend fun fetchExerciseCategories(accessToken: String) {
//        viewModelScope.launch {
            val response = exerciseRepository.fetchExerciseCategory(accessToken)
            if (response.isSuccessful) {
                exerciseCategoryLiveData.postValue(response.body())

            } else {
                val errorMsg = response.errorBody()?.string()
                errorLiveData.postValue(errorMsg)
            }
        }


    }


