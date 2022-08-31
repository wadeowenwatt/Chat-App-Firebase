package com.example.chatapp.ui.walkthrough

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.chatapp.databinding.FragmentWalkthroughBinding

class WalkthroughFragment : Fragment() {

    private var _binding: FragmentWalkthroughBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWalkthroughBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartMessaging.setOnClickListener {
            it.findNavController()
                .navigate(WalkthroughFragmentDirections.actionWalkthroughFragmentToVerification1Fragment())
        }
    }

}