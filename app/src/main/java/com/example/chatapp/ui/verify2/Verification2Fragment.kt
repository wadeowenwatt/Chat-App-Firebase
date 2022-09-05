package com.example.chatapp.ui.verify2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.chatapp.databinding.FragmentVerification2Binding
import com.google.firebase.auth.FirebaseAuth

class Verification2Fragment : Fragment() {

    private var _binding: FragmentVerification2Binding? = null
    private val binding get() = _binding!!

    private lateinit var mAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVerification2Binding.inflate(inflater)

        mAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

}