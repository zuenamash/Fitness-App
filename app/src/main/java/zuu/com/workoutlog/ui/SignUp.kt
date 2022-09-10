package zuu.com.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import zuu.com.workoutlog.databinding.ActivitySignUp2Binding
import zuu.com.workoutlog.models.RegisterRequest
import zuu.com.workoutlog.models.loginResponse
import zuu.com.workoutlog.retrofit.ApiInterface

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUp2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnEnter.setOnClickListener {
            validateSign()

        }
        binding.btnBehind.setOnClickListener {
            val intent= Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
    }

    fun validateSign() {
        var name = binding.tieName.text.toString()
//        var name2 = binding.etLastName.text.toString()
        var phone = binding.textInputEditText.text.toString()
        var mail = binding.tetEmail.text.toString()
        var pass = binding.tetPassword.text.toString()
        var confirm = binding.tetConfirm.text.toString()

        var error= false
        if (name.isBlank()) {
            binding.tilFname.error = "firstname_required"
        }
//        if (name.isBlank()) {
//            error =true
//            binding.tilLastName.error = "Last name required"
//        }
        if (pass.isBlank()) {
            error =true
            binding.textInputEditText.error = "number_required"
        }

        if (mail.isBlank()) {
            error =true
            binding.tilEmail.error = "email_required"
        }
        if (phone.isBlank()) {
            error =true
            binding.tilPassword.error = "password_required"
        }
        if (confirm.isBlank()) {
            error =true
            binding.tilConfirm.error = "confirmpassword_required"
        }
        if (pass!= confirm) {
            error =true
            binding.tilPassword.error="password do not match"
        }
        if(!error){
            val  registerRequest=RegisterRequest(name, name, mail, phone, pass)

            makeRegistrationRequest(registerRequest)
        }
    }
    fun makeRegistrationRequest(registerRequest: RegisterRequest){
        var apiClient=ApiInterface.buildApiInterface(ApiInterface::class.java)
        var  request= apiClient.retrofit(registerRequest)

        request.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    var message = response.body()?.message
                    Toast.makeText(baseContext,message,Toast.LENGTH_LONG).show()
                    //intent to login
                    startActivity(Intent(baseContext, loginResponse::class.java))


                }else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })

    }
}


//        binding.btnEnter.setOnClickListener {
//            var intent = Intent(this, workOut::class.java)
//            startActivity(intent)
//
//        }
//        binding.btnBehind.setOnClickListener {
//            var intent = Intent(this, loginResponse::class.java)
//            startActivity(intent)
//            validateSignUP()
//        }
//    }
//
//    fun validateSignUP() {
//        var error = false
//        binding.tilEmail.error = null
//        binding.tilPassword.error = null
//        var name = binding.tieName.text.toString()
//        var last = binding.textInputEditText.text.toString()
//        var email = binding.tetEmail.text.toString()
//        var password = binding.tetPassword.text.toString()
//        var confirm = binding.tetConfirm.text.toString()
//        if (name.isBlank()) {
//            binding.tilFname.error
//            ("firstname is required")
//            error = true
//        }
//
//        if (last.isBlank()) {
//            binding.tilFname.error
//            ("Lastname is required")
//            error = true
//        }
//        if (email.isBlank()) {
//            binding.tilEmail.error
//            print("Email is required")
//            error = true
//        }
//        if (password != confirm)
//            binding.tilConfirm.error
//        print("Password do not match")
//

//            if (!error) {
//                val registerRequest = RegistrationRequest(name,last, email, phoneNumber, password)
//            }
    }
//    fun makeRegistrationRequest(registerRequest: NewRegesterM){
//        var apiClient= ApiInterface.buildApiInterface(ApiInterface::class.java)
//        var request =apiClient.(Respons)
//        request.enqueue(object:Callback<RegisterResponse>{
//            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
//                if (response.isSuccessful) {
//                    var message = response.errorBody()?.string()
//                    Toast.makeText(baseContext,message, Toast.LENGTH_LONG).show()
//                    //intent to login
//                } else {
//                    val error = response.errorBody()?.string()
//                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
//                }
//            }
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//
//
//}

    class RegisterResponse {

    }
}
//    fun makeRegistrationRequest(registerRequest: registerRequest){
//        var ApiInterface = ApiInterface.buildApiInterface(ApiInterface::class.java)
//        val request = ApiInterface.registerUser(registerRequest)
//
//        request.enqueue(object: Callback<NewRegisterM> {
//
//        })
//    }
//}




