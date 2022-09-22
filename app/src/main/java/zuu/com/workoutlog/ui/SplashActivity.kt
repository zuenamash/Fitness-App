package zuu.com.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import zuu.com.workoutlog.LoginActivity


class workOut : AppCompatActivity() {

    lateinit var sharedPref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref= getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        val accessToken = sharedPref.getString("ACCESS_TOKEN", "")
        if (accessToken!!.isNotBlank()) {
            startActivity(Intent(this, HomeActivity::class.java))

        }
        else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}

