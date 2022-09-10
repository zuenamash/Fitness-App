package zuu.com.workoutlog

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import zuu.com.workoutlog.databinding.ActivityLoginBinding
import zuu.com.workoutlog.models.loginRequest
import zuu.com.workoutlog.models.loginResponse
import zuu.com.workoutlog.retrofit.ApiInterface
import zuu.com.workoutlog.ui.SignUp
import android.content.SharedPreferences as SharedPreferences1

class LoginActivity : AppCompatActivity() {
  lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            val intent=Intent(this, HomeActivity::class.java)
            startActivity(intent)
            validateLogin()
        }
        binding.tvSignup.setOnClickListener {
            val intent=Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener{
            val intent=Intent(HomeActivity,this,LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
    fun validateLogin(){
        var email=binding.etEmail.text.toString()
        var password =binding.etPassword.text.toString()
        var error=false
        if (email.isBlank()){
            binding.tilEmal.error="email_required"
            error=true
        }
        if (password.isBlank()){
            binding.tilPassword.error="Password required"
            error=true
        }
        if(!error){
            var loginRequest= loginRequest(email,password)
            binding.pgProgress.visibility= View.VISIBLE
            makeloginRequest(loginRequest)
        }
    }
    fun makeloginRequest(loginRequest:loginRequest){
        var apiClient= ApiInterface.buildApiInterface((ApiInterface::class.java))
        var request =apiClient.(loginRequest)
        request.enqueue(object : Callback<loginResponse> {
            override fun onResponse(call: Call<loginResponse>, response: Response<loginResponse>) {
                if (response.isSuccessful){
                    binding.tvSignup.visibility= View.GONE
                    val loginResponse=response.body()
                    saveLoginDetails(loginResponse!!)
                    Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext, HomeActivity::class.java))
                    finish()
                }
                else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<loginResponse>, t: Throwable) {
                binding.tvSignup.visibility= View.GONE
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun saveLoginDetails(LoginResponse:loginResponse){
        val editor= SharedPreferences1().edit()
        editor.putString("ACCESS_TOKEN", LoginResponse.accessToken)
        editor.putString("USER_ID",LoginResponse.userId)
        editor.putString("PROFILE_ID",LoginResponse.profileId)
        editor.apply()

        val public = null
        @Override
        public void onClick(View arg0) {
            SharedPreferences1 myPrefs = getSharedPreferences("Activity",
            MODE_PRIVATE)
            SharedPreferences1.Editor editor = myPrefs . edit ();
            editor.clear();
            editor.commit();
            //AppState.getSingleInstance().setLoggingOut(true);
            setLoginState(true);
            Log.d(TAG, "Now log out and start the activity login");
            val new = null
            intent = new intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
    }

    private fun setLoginState(b: Boolean) {

    }

    private void setLoginState(boolean status) {
        SharedPreferences1 sp = getSharedPreferences ("LoginState",
        MODE_PRIVATE);
        SharedPreferences1.Editor ed = sp . edit ();
        ed.putBoolean("setLoggingOut", status);
        ed.commit();
    }

}










