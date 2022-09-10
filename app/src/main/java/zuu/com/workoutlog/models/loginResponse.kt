package zuu.com.workoutlog.models

import com.google.gson.annotations.SerializedName

data class loginResponse(
   var message:String,
   @SerializedName("access_token")var accessToken:String,
   @SerializedName("user_id")var userId:String,
   @SerializedName("profile_Id")var profileId:String




)


