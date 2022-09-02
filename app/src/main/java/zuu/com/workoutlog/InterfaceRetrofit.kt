package zuu.com.workoutlog

import retrofit2.Call
import retrofit2.http.Body

interface InterfaceRetrofit {
    @POST("/register")
    fun registerUser(@Body registerRequest:RegisterRequest):Call<RegisterRequest>
}