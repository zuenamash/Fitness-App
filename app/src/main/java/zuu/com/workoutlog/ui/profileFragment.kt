package zuu.com.workoutlog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import zuu.com.workoutlog.databinding.FragmentProfileBinding
import zuu.com.workoutlog.models.ProfileRequest
import zuu.com.workoutlog.viewModel.UserViewModel


class profileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel =
            ViewModelProvider(this).get(userViewModel::class.java)
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnprofilelogin.setOnClickListener {
            validateProfile()
        }

    }

fun validateProfile() {
    var gender = binding.etGender.text.toString()
    var dateofbirth = binding.etdob.text.toString()
    var weight = binding.etweight.text.toString()
    var height = binding.etheight.text.toString()
    var error = false
    if (gender.isBlank()) {
        binding.tilgender.error = "gender required"
    }
    if (dateofbirth.isBlank()) {
        binding.tildob.error = "date of birth name required"
    }
    if (weight.isBlank()) {
        binding.tilweight.error = "weight is required"

    }
    if (height.isBlank()) {
        binding.tilheight.error = "Email reuired"
    }
    if (!error) {
        val profilerequest = ProfileRequest(gender,dateofbirth,weight,height)
//        UserViewModel.profileRequest(profilerequest)

    }

}}

