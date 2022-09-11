package zuu.com.workoutlog

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import  androidx.lifecycle.Observer
import zuu.com.workoutLog.R
import zuu.com.workoutLog.databinding.ActivityLoginBinding
import zuu.com.workoutlog.models.loginRequest
import zuu.com.workoutlog.models.loginResponse
import zuu.com.workoutlog.ui.HomeActivity
import zuu.com.workoutlog.ui.SignUp
import zuu.com.workoutlog.viewModel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: android.content.SharedPreferences
     val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            validateLogIn()
//            finish()
        }

        binding.tvSighUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse->
            saveLoginDetails(loginResponse!!)
            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,HomeActivity::class.java))
            finish()
        })

        userViewModel.loginErrorLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }
    fun validateLogIn(){
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var error=false

        if (email.isBlank()){
            binding.tilEmail.error = getString(R.string.email_required)
            error=true
        }
        else if (password.isBlank()){
            binding.tilPassword.error = getString(R.string.imput_password)
            error=true
        }
//        else if (password.length<8){
//            binding.tilPassword.error = getString
//            error=true
//        }
        else if (password.length>16){
            binding.tilPassword.error = getString(R.string.confirm_password)
            error=true
        }
        if (!error){
            var loginRequest=loginRequest(email, password)
            binding.pbLogin.visibility=View.VISIBLE
            userViewModel.loginUser(loginRequest)
//            makeLoginRequest(loginRequest)

        }
    }
    fun saveLoginDetails(loginResponse: loginResponse){
        val editor=sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()
    }
}
