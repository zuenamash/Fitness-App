package zuu.com.workoutlog.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zuu.com.workoutlog.LoginActivity
import zuu.com.workoutlog.models.RegisterRequest
import zuu.com.workoutlog.models.RegisterResponse
import zuu.com.workoutlog.models.loginRequest
import zuu.com.workoutlog.models.loginResponse
import zuu.com.workoutlog.repository.UserRepository

class UserViewModel : ViewModel(){
    val userRepository=UserRepository()
    val loginResponseLiveData = MutableLiveData<loginResponse>()
    val loginErrorLiveData = MutableLiveData<String>()
    val registerRequestLiveData=MutableLiveData<RegisterResponse>()
    val registerErrorLiveData= MutableLiveData<String?>()

    fun loginUser(loginRequest: loginRequest){
        viewModelScope.launch{
            val response= userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())

            }
            else{
                val error=response.errorBody()?.string()
                loginErrorLiveData.postValue(error!!)
            }
        }
    }
    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response=userRepository.makeUserRequest(registerRequest)
            if (response.isSuccessful){
                registerRequestLiveData.postValue(response.body())
            }
        }
    }

}
