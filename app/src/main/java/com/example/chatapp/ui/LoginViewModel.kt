package com.example.chatapp.ui

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class LoginViewModel : ViewModel() {

    private var _countryCode: MutableLiveData<String>? = null
    val countryCode : LiveData<String> get() = _countryCode!!

    private var _verificationId : MutableLiveData<String>? = null
    val verificationId : LiveData<String> get() = _verificationId!!


}