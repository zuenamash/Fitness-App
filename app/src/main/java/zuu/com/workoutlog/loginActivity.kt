package zuu.com.workoutlog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class loginActivity : AppCompatActivity() {
    lateinit var btnLogin:Button
    lateinit var tilEmail: TextInputLayout
    lateinit var tilPassword:TextInputLayout
    lateinit var etEmail:TextInputEditText
    lateinit var etPassword:TextInputEditText
    lateinit var tvSignup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin=findViewById(R.id.btnLogin)
        tilEmail=findViewById(R.id.tilEmal)
        tilPassword=findViewById(R.id.tilPassword)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        tvSignup =findViewById(R.id.tvSignup)




        btnLogin.setOnClickListener {
            var intent=Intent(this,workOut::class.java)
            startActivity(intent)
            validateLogin()

        }

        tvSignup.setOnClickListener{
            var intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }

    }
    fun validateLogin(){
        var email= etEmail.text.toString()
        var password=etPassword.text.toString()

        if (email.isBlank()){
            tilEmail.error=getString(R.string.email_required)
            error(true)
        }
        if (password.isBlank()){
            tilPassword.error="password required"
            error(true)
        }


    }
}