package zuu.com.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zuu.com.workoutlog.databinding.ActivityHomeBinding
import zuu.com.workoutlog.models.loginResponse
import zuu.com.workoutlog.ui.PlanFragment
import zuu.com.workoutlog.ui.profileFragment
import zuu.com.workoutlog.ui.track

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castviews()
        setUpBottomNav()

        binding.tvLogout.setOnClickListener {
            val editor=sharedprefs.edit()
            editor.putString("ACCESS_TOKEN", "")
            editor.putString("USER_ID", "")
            editor.putString("PROFILE_ID", "")
            editor.apply()
//            startActivity(Intent(this,LoginResponse::class.java))
            startActivity(Intent(this,loginResponse::class.java))

        }
    }

    fun castviews(){
        binding.fcvHome
        binding.bnv
    }

    fun setUpBottomNav(){
        binding.bnv.setOnItemSelectedListener { item->
            when (item.itemId){
                R.id.plan->{
                    val transaction=supportFragmentManager.beginTransaction().replace(R.id.fcvHome,
                        PlanFragment()
                    ).commit()
                    true
                }
                    R.id.track->{
                        val transaction=supportFragmentManager.beginTransaction().replace(R.id.fcvHome,
                            track()
                        ).commit()
                        true
                    }
                R.id.profile->{
                    val transaction=supportFragmentManager.beginTransaction().replace(R.id.fcvHome,
                        profileFragment()
                    ).commit()
                    true
                }
                else->false
            }
        }
    }}
