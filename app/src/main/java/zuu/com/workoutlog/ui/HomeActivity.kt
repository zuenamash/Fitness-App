package zuu.com.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zuu.com.workoutLog.R
import zuu.com.workoutLog.databinding.ActivityHomeBinding
import zuu.com.workoutlog.LoginActivity

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLog.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            logOutRequest()
        }

        castView()
        setupBottomNav()
    }

    fun castView() {
        binding.fcvHome
        binding.bnv
    }

    fun setupBottomNav() {
        binding.bnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome, PlanFragment())
                    transaction.commit()
                    true
                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PlanFragment()).commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, profileFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
    fun logOutRequest(){
        sharedPrefs.edit().clear().commit()
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}