package com.example.chatapp.ui.act_login.verify1

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
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
    var formatPhoneNumber: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            formatPhoneNumber =
                countryCode + formatPhoneNumber(binding.phoneNum.text.toString())
            if (TextUtils.isEmpty(binding.phoneNum.text)) {
                binding.phoneNum.error = "Empty Phone Number!"
            } else {
                sendVerificationCode(formatPhoneNumber)
            }
        }

        binding.layout2.pinCode.setOnPinEnteredListener {
            val code = it.subSequence(0, 6).toString()
            verifyPhoneNumberWithCode(verificationId, code)
        }

    }

    private val mCallbacks = object :
        PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(phoneAuthCredential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            binding.phoneNum.error = "Invalid Phone Number"
//            Toast.makeText(
//                requireContext(),
//                "Invalid Phone Number",
//                Toast.LENGTH_LONG
//            ).show()
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            this@Verification1Fragment.verificationId = verificationId
            forceResendingToken = token

            // change layout
            binding.layout1.visibility = View.GONE
            binding.layout2.guideEnterCode.text =
                getString(R.string.title_guide_code, formatPhoneNumber)
            binding.layout2.layoutCode.visibility = View.VISIBLE
        }
    }

    private fun formatPhoneNumber(phoneNumber: String): String {
        if (phoneNumber.length == 10) {
            return phoneNumber.substring(1)
        } else
            return phoneNumber
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
                findNavController()
                    .navigate(Verification1FragmentDirections.actionVerification1FragmentToUserProfileFragment())
            }
            .addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    "Wrong code!!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


}