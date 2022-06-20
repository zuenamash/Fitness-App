package zuu.com.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import zuu.com.workoutlog.databinding.ActivityHomeBinding
import zuu.com.workoutlog.databinding.ActivitySignUp2Binding

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
            var intent =Intent(this,loginActivity::class.java)
            startActivity(intent)
            validateSignUP()
        }
    }
        fun validateSignUP(){
            var error=false
            binding.tilEmail.error=null
            binding.tilPassword.error=null
            var name=binding.tieName.text.toString()
            var Last= binding.textInputEditText.text.toString()
            var Email=binding.tetEmail.text.toString()
            var Password=binding.tetPassword.text.toString()
            var Confirm=binding.tetConfirm.text.toString()
            if (name.isBlank()){
                binding.tilFname.error
                ("firstname is required")
                error=true}

            if (Last.isBlank()){
                binding.tilFname.error
                ("Lastname is required")
                error=true
            }
            if (Email.isBlank()){
                binding.tilEmail.error
                print("Email is required")
                error=true
            }
            if (Password!=Confirm)
            binding.tilConfirm.error
            print("Password do not match")

            }
    }




