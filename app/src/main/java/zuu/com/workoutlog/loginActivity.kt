package zuu.com.workoutlog

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import zuu.com.workoutlog.databinding.ActivityLoginBinding
import zuu.com.workoutlog.ui.SignUp

class loginActivity : AppCompatActivity() {
  lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            var intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
            validateLogin()
        }
        binding.tvSignup.setOnClickListener{
            var intent=Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
    fun validateLogin(){
        var email= binding.etEmail.text.toString()
        var password=binding.etPassword.text.toString()
        var error=false

        if (email.isBlank()){
            binding.tilEmal.error="Input email"
            error=true
        }
        if (password.isBlank()){
            binding.tilPassword.error="password required"
            error=true
        }
        if(!error){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }
}