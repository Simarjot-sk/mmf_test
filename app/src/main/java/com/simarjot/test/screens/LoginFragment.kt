package com.simarjot.test.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.material.snackbar.Snackbar
import com.simarjot.test.R

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val callbackManager = CallbackManager.Factory.create()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginButton = view.findViewById(R.id.login_button) as LoginButton
        loginButton.setPermissions("public_profile", "email")
        loginButton.fragment = this
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                showSnackBar("logged in successfully")
            }

            override fun onCancel() {
                showSnackBar("login cancelled")
            }

            override fun onError(exception: FacebookException) {
                showSnackBar("login failed: ${exception.localizedMessage}")
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}

fun Fragment.showSnackBar(text: String) {
    Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
}