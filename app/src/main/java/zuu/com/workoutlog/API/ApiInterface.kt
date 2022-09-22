package zuu.com.workoutlog.API

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header
import zuu.com.workoutlog.models.*


interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: loginRequest): Response<loginResponse>

    @GET("/exercise")
    suspend fun fetchExerciseCategories(@Header("Authorization") accessToken: String): Response<List<ExerciseCategory>>
}