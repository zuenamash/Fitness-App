package zuu.com.workoutlog.ui

import android.content.Intent
import android.content.SyncRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Callback
import zuu.com.workoutlog.ApiInterface
import zuu.com.workoutlog.databinding.ActivitySignUp2Binding
import zuu.com.workoutlog.loginActivity
import java.util.*

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUp2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnter.setOnClickListener {
            var intent = Intent(this, workOut::class.java)
            startActivity(intent)

        }
        binding.btnBehind.setOnClickListener {
            var intent =Intent(this, loginActivity::class.java)
            startActivity(intent)
            validateSignUP()
        }
    }
        fun validateSignUP() {
            var error = false
            binding.tilEmail.error = null
            binding.tilPassword.error = null
            var name = binding.tieName.text.toString()
            var last = binding.textInputEditText.text.toString()
            var email = binding.tetEmail.text.toString()
            var password = binding.tetPassword.text.toString()
            var confirm = binding.tetConfirm.text.toString()
            if (name.isBlank()) {
                binding.tilFname.error
                ("firstname is required")
                error = true
            }

            if (last.isBlank()) {
                binding.tilFname.error
                ("Lastname is required")
                error = true
            }
            if (email.isBlank()) {
                binding.tilEmail.error
                print("Email is required")
                error = true
            }
            if (password != confirm)
                binding.tilConfirm.error
            print("Password do not match")


            if (!error) {
                val registerRequest = registerRequest(name,last, email, phoneNumber, password)
            }
        }
    fun makeRegistrationRequest(registerRequest: registerRequest){
        var ApiInterface = ApiInterface.buildApiInterface(ApiInterface::class.java)
        val request = ApiInterface.registerUser(registerRequest)

        request.enqueue(object: Callback<NewRegisterM> {

        })
    }
}




