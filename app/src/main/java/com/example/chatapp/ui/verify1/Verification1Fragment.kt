package com.example.chatapp.ui.verify1

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.chatapp.databinding.FragmentVerification1Binding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class Verification1Fragment : Fragment() {

    private var _binding: FragmentVerification1Binding? = null
    private val binding get() = _binding!!


    private var countryCode: String = "+"
    private var verificationId: String = ""
    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken? =
        null
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVerification1Binding.inflate(inflater)

        mAuth = FirebaseAuth.getInstance()

        countryCode += binding.countryCode.selectedCountryCode.toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding.btnContinue.setOnClickListener {
            val formatPhoneNumber =
                countryCode + binding.phoneNum.text.toString()
            if (TextUtils.isEmpty(binding.phoneNum.text)) {
                Toast.makeText(
                    requireContext(),
                    "Empty phone number",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                sendVerificationCode(formatPhoneNumber)

            }
        }

        binding.layout2.pinCode.setOnPinEnteredListener {
            Log.e("1", it.subSequence(0,6).toString())
        }

    }

    private val mCallbacks = object :
        PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(phoneAuthCredential)
        }

        override fun onVerificationFailed(e: FirebaseException) {

        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            this@Verification1Fragment.verificationId = verificationId
            forceResendingToken = token

            // change layout
            binding.layout1.visibility = View.GONE
            binding.layout2.layoutCode.visibility = View.VISIBLE
        }
    }

    private fun sendVerificationCode(phone: String) {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(mCallbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun resendVerificationCode(
        phone: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(mCallbacks)
            .setForceResendingToken(token!!)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyPhoneNumberWithCode(
        verificationId: String?,
        code: String
    ) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                //Login success
                val phone = mAuth.currentUser?.phoneNumber
            }
            .addOnFailureListener {

            }
    }
}