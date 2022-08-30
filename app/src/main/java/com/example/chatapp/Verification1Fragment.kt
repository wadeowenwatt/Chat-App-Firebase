package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.chatapp.databinding.FragmentVerification1Binding


class Verification1Fragment : Fragment() {

    private var _binding: FragmentVerification1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVerification1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            it.findNavController().popBackStack()
        }
        binding.btnContinue.setOnClickListener {
            it.findNavController()
                .navigate(Verification1FragmentDirections.actionVerification1FragmentToVerification2Fragment())
        }
    }
}