package com.example.chatapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private var mAuth: FirebaseAuth? = null

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
            } else
                it.findNavController()
                    .navigate(Verification1FragmentDirections.actionVerification1FragmentToVerification2Fragment())
                sendVerificationCode(formatPhoneNumber)


        }
    }

    private fun sendVerificationCode(phone: String) {
        val options = PhoneAuthOptions.newBuilder(mAuth!!)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
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