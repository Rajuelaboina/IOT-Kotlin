package com.thingspeak.upw_iot.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.thingspeak.upw_iot.R
import com.thingspeak.upw_iot.databinding.ActivityLoginBinding
import com.thingspeak.upw_iot.listeners.LoginCallBack
import com.thingspeak.upw_iot.model.User
import com.thingspeak.upw_iot.utils.CheckNetworkConnection
import com.thingspeak.upw_iot.utils.ProgressUtill
import com.thingspeak.upw_iot.utils.SharedPrefManager
import com.thingspeak.upw_iot.viewmodel.LoginViewModel
import com.thingspeak.upw_iot.viewmodelhelper.LoginViewModelHelper


class LoginActivity : AppCompatActivity(), LoginCallBack {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login)
        val loginCheck: Boolean = SharedPrefManager.getInstance(applicationContext).isUserLoggedIn()

        //user already logged in
        if (loginCheck) {
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            finish()
        }
        binding.loginViewModel = ViewModelProvider(this, LoginViewModelHelper(this))[LoginViewModel::class.java]
        /*binding.LoginButton.setOnClickListener {
            val name: String = binding.editTextTextPersonName.text.toString()
            val password: String = binding.editTextTextPassword.text.toString()
            if (isValidations(name, password)) {
                if (name == "MSL" && password == "msl@2023") {
                    moveToNextScreen(name,password)

                } else if (name == "user1" && password == "user1@2023") {
                    moveToNextScreen(name,password)
                } else if (name == "user2" && password == "user2@2023") {
                    moveToNextScreen(name,password)
                } else {
                    Toast.makeText(applicationContext,"username or password is incorrect",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "fields are not empty", Toast.LENGTH_SHORT).show()
            }
        }*/

    }

    private fun moveToNextScreen(name: String, password: String) {
        if (CheckNetworkConnection.isInternetOn(applicationContext)){
            SharedPrefManager.getInstance(applicationContext).insetUserData(User(name, password))
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            finish()
        }else{
            Snackbar.make(binding.constraintLayoutLogin,"check Internet connection", Snackbar.LENGTH_LONG).show()
            ProgressUtill.hideProgress(applicationContext)
        }

    }

    private fun isValidations(name: String, password: String): Boolean {
        val boo = true
        return if (name.isEmpty() && password.isEmpty()) {
            false
        } else boo
    }

    override fun onError(code: Int) {
        if (code==1){
            binding.usernameTextInputLayout.error="username is incorrect"
        }else if (code==2){
            binding.passwordTextInputLayout.error="password is incorrect"
        }


    }

    override fun onSuccess() {
        val name: String = binding.editTextTextPersonName.text.toString().trim()
        val password: String = binding.editTextTextPassword.text.toString().trim()
        moveToNextScreen(name,password)
    }

    override fun onRefresh() {
        binding.usernameTextInputLayout.error=""
        binding.passwordTextInputLayout.error=""
    }
}