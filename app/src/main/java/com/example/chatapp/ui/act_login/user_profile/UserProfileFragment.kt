package com.example.chatapp.ui.act_login.user_profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chatapp.ui.act_chat.activity.ChatActivity
import com.example.chatapp.databinding.FragmentUserProfileBinding
import com.example.chatapp.domain.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserProfileFragment : Fragment() {

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserProfileBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val user = User(
                binding.firstName.text.toString(),
                binding.lastName.text.toString()
            )

            val uid = Firebase.auth.uid

            db.collection("/users").document(uid.toString()).set(user)
                .addOnSuccessListener {
                    val intent =
                        Intent(requireContext(), ChatActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)

                }
                .addOnFailureListener {
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong!",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }

        binding.avatarImage.setOnClickListener { }

    }

}