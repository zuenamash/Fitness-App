package zuu.com.workoutlog.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zuu.com.workoutlog.models.*
import zuu.com.workoutlog.repository.UserRepository

class UserViewModel : ViewModel() {
    val userRepository = UserRepository()
    val loginResponseLiveData = MutableLiveData<loginResponse>()
    val loginErrorLiveData = MutableLiveData<String>()
    val registerRequestLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData = MutableLiveData<String?>()
    val profileErrorLiveData = MutableLiveData<String?>()
    val profileResponseLiveData = MutableLiveData<String?>()

    fun loginUser(loginRequest: loginRequest) {
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful) {
                loginResponseLiveData.postValue(response.body())

            } else {
                val error = response.errorBody()?.string()
                loginErrorLiveData.postValue(error!!)
            }
        }
    }

    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val response = userRepository.makeUserRequest(registerRequest)
            if (response.isSuccessful) {
                registerRequestLiveData.postValue(response.body())
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)

            } else {
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }
    fun profileUser(profileRequest: ProfileRequest){
        viewModelScope.launch {
            val response=userRepository.profileUser(profileRequest)
            if (response.isSuccessful){
                profileResponseLiveData.postValue("response.body()")

            }else{
                val error=response.errorBody()?.string()
                profileErrorLiveData.postValue(error)
            }
        }

    }


}