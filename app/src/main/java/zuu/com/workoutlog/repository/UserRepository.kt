package zuu.com.workoutlog.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import zuu.com.workoutlog.API.ApiClient
import zuu.com.workoutlog.API.ApiInterface
import zuu.com.workoutlog.models.RegisterRequest
import zuu.com.workoutlog.models.RegisterResponse
import zuu.com.workoutlog.models.loginRequest

class UserRepository {
    //    creating instance of our Api client
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: loginRequest) = withContext(Dispatchers.IO) {
        val response = apiClient.login(loginRequest)
        return@withContext response
    }

    suspend fun makeUserRequest(registerRequest: RegisterRequest): Response<RegisterResponse> =
        withContext(Dispatchers.IO) {
            val response = apiClient.registerUser(registerRequest)
            return@withContext response


        }

}