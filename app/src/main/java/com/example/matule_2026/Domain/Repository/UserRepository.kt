package com.example.matule_2026.Domain.Repository

import android.content.Context
import android.content.SharedPreferences

object UserRepository {
    private lateinit var actSystem: SharedPreferences

     fun init(context: Context){
        actSystem = context.getSharedPreferences("actSystem",Context.MODE_PRIVATE)
    }

     var UserID:String
        get() = actSystem.getString("UserID", "")!!
        set(value) = actSystem.edit().putString("UserID",value).apply()


    var notification: Boolean
        get() = actSystem.getBoolean("notification", true)
        set(value) = actSystem.edit().putBoolean("notification",value).apply()
    var email:String
        get() = actSystem.getString("Email", "")!!
        set(value) = actSystem.edit().putString("Email",value).apply()

     var act:Int
        get() = actSystem.getInt("act", 0)
        set(value) = actSystem.edit().putInt("act",value).apply()


     var Token:String
        get() = actSystem.getString("Token", "")!!
        set(value) = actSystem.edit().putString("Token",value).apply()


    var pinCode: String
        get() = actSystem.getString("pin_code", "").orEmpty()
        set(value) = actSystem.edit().putString("pin_code", value).apply()


    fun isPinCodeSet(): Boolean = pinCode.isNotEmpty()

    fun validatePinCode(input: String): Boolean = pinCode == input
}
