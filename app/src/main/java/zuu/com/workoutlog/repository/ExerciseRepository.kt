package zuu.com.workoutlog.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import zuu.com.workoutlog.API.ApiClient
import zuu.com.workoutlog.API.ApiInterface
import zuu.com.workoutlog.Workoutlog
import zuu.com.workoutlog.database.ExerciseDao
import zuu.com.workoutlog.database.Workoutdb
import zuu.com.workoutlog.models.ExerciseCategory
import zuu.com.workoutlog.models.Exercises

class ExerciseRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)  //Instantiate api client.
    val database= Workoutdb.getDatabase(Workoutlog.appContext)
    val ExerciseCategoryDao = database.ExerciseCategoryDao()
    val ErrorLiveData = MutableLiveData<String>()


    suspend fun fetchExerciseCategory(accessToken: String):Response<List<ExerciseCategory>> {
    return withContext(Dispatchers.IO) {
//        return withContext apiClient.fetchExerciseCategories(accessToken)
        var response = apiClient.fetchExerciseCategories(accessToken)
        if (response.isSuccessful){
            var categories = response.body()
            categories?.forEach { category ->
           ExerciseCategoryDao.insertExerciseCategory(category)

            }
        }
        response
        }
    }
    suspend fun fetchExercises(accessToken: String):Response<List<Exercises>> {
        return withContext(Dispatchers.IO) {
            var response = apiClient.fetchExercises(accessToken)
            if (response.isSuccessful){
                var exercises = response.body()
                exercises?.forEach{ exercise->
                    exerciseDao.insertExercise(exercise) }
            }
            response

    }}

    fun gatDbCategories():LiveData<List<ExerciseCategory>>{
        return ExerciseCategoryDao.getExerciseCategories()
    }
    fun gatDbExercises():LiveData<List<Exercises>>{
        return ExerciseDao.getExercises()

}}
