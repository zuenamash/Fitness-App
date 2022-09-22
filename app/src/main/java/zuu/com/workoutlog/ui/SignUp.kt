package zuu.com.workoutlog.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import zuu.com.workoutlog.LoginActivity
import zuu.com.workoutlog.R
import zuu.com.workoutlog.databinding.ActivitySignUpBinding
import zuu.com.workoutlog.models.RegisterRequest
import zuu.com.workoutlog.viewModel.UserViewModel

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener{
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.tvNext.setOnClickListener {
            validateSign()
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer {registerResponse->
            Toast.makeText(baseContext,registerResponse.message,Toast.LENGTH_LONG).show()
          startActivity(Intent(baseContext,LoginActivity::class.java))
        })
        userViewModel.registerErrorLiveData.observe(this, Observer { regError->
            Toast.makeText(baseContext,regError,Toast.LENGTH_LONG).show()
        })
    }
    fun validateSign() {

        var first=binding.etFirstName.text.toString()
        var last=binding.etLastName.text.toString()
        var email=binding.etEmail.text.toString()
        var password=binding.etPassword.text.toString()
        var confirm=binding.etConfirm.text.toString()
        var phoneNumber=binding.etPhone.text.toString()
        var error=false
        if (first.isBlank()){
            error=true
            binding.tilFirstName.error=getString(R.string.FirstName_required)
        }
        if (last.isBlank()){
            error=true
            binding.tilLastName.error=getString(R.string.LastName_required)
        }

        if (email.isBlank()){
            error=true
            binding.tilEmail.error=getString(R.string.Email_required)
        }
        if (phoneNumber.isBlank()){
            error=true
            binding.tilPhone.error="Phone number is required"
        }
        if (password.isBlank()){
            error=true
            binding.tilPassword.error=getString(R.string.Password_required)
        }
        if (confirm.isBlank()){
            error=true
            binding.tilConfirm.error=getString(R.string.confirm_password)
        }
        if(password!=confirm){
            error=true
            binding.tilConfirm.error="Passwords must match"
        }
        if(!error){
            val registerRequest =RegisterRequest(first,last,email,phoneNumber,password)
//          userViewModel.regesterUser(registerRequest)
//           makeRegistrationRequest(RegisterRequest)

        }
    }
    }






