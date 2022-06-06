package zuu.com.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUp : AppCompatActivity() {
    lateinit var tilFname:TextInputLayout
    lateinit var tieName:TextInputEditText
    lateinit var tetInputEditText: TextInputEditText
    lateinit var tilEmail:TextInputLayout
    lateinit var tetEmail:TextInputEditText
    lateinit var tetPassword:TextInputEditText
    lateinit var tilPassword:TextInputLayout
    lateinit var tetConfirm:TextInputEditText
    lateinit var tilConfirm:TextInputLayout
    lateinit var btnEnter:Button
    lateinit var btnBehind:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)
        btnEnter = findViewById(R.id.btnEnter)
        btnBehind = findViewById(R.id.btnBehind)
        tilFname = findViewById(R.id.tilFname)
        tieName = findViewById(R.id.tieName)
        tetInputEditText = findViewById(R.id.textInputEditText)
        tilEmail = findViewById(R.id.tilEmail)
        tetEmail = findViewById(R.id.tetEmail)
        tetPassword = findViewById(R.id.tetPassword)
        tilPassword = findViewById(R.id.tilPassword)
        tetConfirm = findViewById(R.id.tetConfirm)
        tilConfirm = findViewById(R.id.tilConfirm)

        btnEnter.setOnClickListener {
            var intent = Intent(this, workOut::class.java)
            startActivity(intent)

        }
        btnBehind.setOnClickListener {
            var intent =Intent(this,loginActivity::class.java)
            startActivity(intent)
            validateSignUP()
        }
    }
        fun validateSignUP(){
            var error=false
            tilEmail.error=null
            tilPassword.error=null
            var name=tieName.text.toString()
            var Last= tetInputEditText .text.toString()
            var Email=tetEmail.text.toString()
            var Password=tetPassword.text.toString()
            var Confirm=tetConfirm.text.toString()
            if (name.isBlank()){
                tilFname.error="firstname is required"
                error=true}

            if (Last.isBlank()){
                tilFname.error="Lastname is required"
                error=true
            }
            if (Email.isBlank()){
                tilEmail.error="Email is required"
                error=true
            }
            if (Password.isBlank()){
                tilPassword.error="Password is required"
                error=true
            }
            if (Confirm.isBlank()){
                tilConfirm.error="PasswordConfirmation is required"
                error=true

            }

            if (!error){

            }
        }


    }




