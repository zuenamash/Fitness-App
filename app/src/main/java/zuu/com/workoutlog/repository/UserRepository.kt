package zuu.com.workoutlog.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import zuu.com.workoutlog.API.ApiClient
import zuu.com.workoutlog.API.ApiInterface
import zuu.com.workoutlog.models.ProfileRequest
import zuu.com.workoutlog.models.RegisterRequest
import zuu.com.workoutlog.models.loginRequest

class UserRepository {
    //    creating instance of our Api client
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: loginRequest) = withContext(Dispatchers.IO) {
        val response = apiClient.login(loginRequest)  //invoking the login function
        return@withContext response
    }

    suspend fun makeUserRequest(registerRequest: RegisterRequest) = withContext(Dispatchers.IO) {
            val response = apiClient.registerUser(registerRequest) //invoking the register function
            return@withContext response

        }
    suspend fun profileUser(profileRequest: ProfileRequest)= withContext(Dispatchers.IO){
        val response= apiClient.registerUser(RegisterRequest("Firstname","Lastname",
            "mnachariazuenah@gmail.com","075333888","1111"))
        return@withContext response
    }

}
