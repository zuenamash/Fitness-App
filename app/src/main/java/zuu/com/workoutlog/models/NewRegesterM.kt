package zuu.com.workoutlog.models

import com.google.gson.annotations.SerializedName

data class NewRegesterM(
    @SerializedName("first_name")var firstName:String,
    @SerializedName("last_name")var lastName:String,
    var email:String,
    @SerializedName("phone_number")var phoneBook:String,
    var password:String
)
