package zuu.com.workoutlog.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import zuu.com.workoutlog.models.*


interface InterfaceRetrofit {
    @POST("/register")
    fun registerUser(@Body registerRequest:RegisterRequest):Call<RegisterResponse>

    @POST("/login")
    fun login(@Body loginRequest:loginResponse): Call<loginRequest>
}