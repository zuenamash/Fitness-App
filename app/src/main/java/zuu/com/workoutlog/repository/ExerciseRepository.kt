package zuu.com.workoutlog.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import zuu.com.workoutlog.API.ApiClient
import zuu.com.workoutlog.API.ApiInterface

class ExerciseRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)  //Instantiate api client.
    suspend fun fetchExerciseCategory(accessToken: String)= withContext(Dispatchers.IO){
        // The above code switches courotine to another thread.
        return@withContext apiClient.fetchExerciseCategories(accessToken)

    }
}
