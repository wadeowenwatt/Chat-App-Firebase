package com.example.chatapp

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.databinding.ActivityMainBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var countryCode: String = "+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

//        countryCode += binding.countryCode.selectedCountryCode.toString()
//
//        binding.btnLogin.setOnClickListener {
//            val formatPhoneNumber =
//                countryCode + binding.phoneNum.text.toString()
//            if (TextUtils.isEmpty(binding.phoneNum.text)) {
//                Toast.makeText(this, "Empty phone number", Toast.LENGTH_SHORT)
//                    .show()
//            } else
//                sendVerificationCode(formatPhoneNumber)
//        }
    }

    private fun sendVerificationCode(phone: String) {
        val options = PhoneAuthOptions.newBuilder(mAuth!!)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object :
                PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                }

                override fun onVerificationFailed(p0: FirebaseException) {

                }

            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}