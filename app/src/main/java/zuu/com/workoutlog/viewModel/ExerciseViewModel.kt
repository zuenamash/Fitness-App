package zuu.com.workoutlog.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zuu.com.workoutlog.models.ExerciseCategory
import zuu.com.workoutlog.repository.ExerciseRepository


class ExerciseViewModel: ViewModel() {
    val exerciseRepository = ExerciseRepository()
    val exerciseCategoryLiveData = MutableLiveData<List<ExerciseCategory>>()
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
}

