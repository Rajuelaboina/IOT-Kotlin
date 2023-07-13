package com.thingspeak.upw_iot.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import com.thingspeak.upw_iot.model.User
import com.thingspeak.upw_iot.ui.activity.LoginActivity


class SharedPrefManager {

    companion object{
        lateinit var context:Context
        fun getInstance(context: Context): SharedPrefManager {
            Companion.context =context
            var sharedPrefManager : SharedPrefManager? =null
            if (sharedPrefManager==null){
                sharedPrefManager = SharedPrefManager()
            }
            return sharedPrefManager
        }
    }

    fun insetUserData(user: User) {
        val sharedPreferences = context.getSharedPreferences("MyIOTSharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString("name", user.name)
        myEdit.putString("password", user.password)
        myEdit.putBoolean("OK", true)
        myEdit.apply()
    }
    fun getUserData(): User {
        val sharedPreferences = context.getSharedPreferences("MyIOTSharedPref", MODE_PRIVATE)
        return User(
            sharedPreferences.getString("name", null),
            sharedPreferences.getString("password", null)
        )
    }
    fun isLogedout() {
        val sharedPreferences = context.getSharedPreferences("MyIOTSharedPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
    fun isUserLoggedIn(): Boolean {
        val sharedPreferences = context.getSharedPreferences("MyIOTSharedPref", MODE_PRIVATE)
        return sharedPreferences.getString("name", null) != null
    }
}