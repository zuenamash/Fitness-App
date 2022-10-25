package zuu.com.workoutlog.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import zuu.com.workoutlog.R
import zuu.com.workoutlog.Utilities.Constants
import zuu.com.workoutlog.databinding.ActivityHomeBinding
import zuu.com.workoutlog.models.loginResponse
import zuu.com.workoutlog.viewModel.ExerciseViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs:SharedPreferences
    val exerciseViewModel : ExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
        setupBottomNav()

        sharedPrefs = getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)
        val token = sharedPrefs.getString(Constants.accessToken, Constants.emptyString)
        exerciseViewModel.fetchExerciseCategories(token!!) //Can not be null

        binding.tvLog.setOnClickListener {
            val editor = sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN", "")
            editor.putString("USER_ID", "")
            editor.putString("PROFILE_ID", "")
            editor.apply()
            startActivity(Intent(this, loginResponse::class.java))

        }
    }

//    fun triggerFetchExerciseCategory(){
//
//    }
         override fun onResume() {
            super.onResume()
             exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCateg ->
                 Toast.makeText(baseContext, "fetached ${exerciseCateg.size} categosries ",
                     Toast.LENGTH_LONG
                 ).show()
             }  )
             exerciseViewModel.errorLiveData.observe(this, Observer {
                 error->
                 Toast.makeText(this,error,Toast.LENGTH_LONG).show()
             })
    }
    fun castView() {
        binding.fcvHome
        binding.bnv
    }

    fun setupBottomNav() {
        binding.bnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome,PlanFragment())
                    .commit()
                    true
                }

                R.id.track -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, trackFragment())
                        .commit()
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
    companion object {
        fun getIntent(context:Context):Intent {
            return  Intent(context,HomeActivity::class.java)
        }
//    fun logOutRequest(){
//        sharedPrefs.edit().clear().commit()
//        startActivity(Intent(this,LoginActivity::class.java))
//        finish()
    }
}

