package zuu.com.workoutlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zuu.com.workoutlog.databinding.ActivityHomeBinding
import zuu.com.workoutlog.ui.PlanFragment
import zuu.com.workoutlog.ui.profileFragment
import zuu.com.workoutlog.ui.track

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBottomNav()
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
