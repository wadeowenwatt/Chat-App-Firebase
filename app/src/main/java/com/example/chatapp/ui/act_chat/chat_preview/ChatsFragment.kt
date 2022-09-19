package com.example.chatapp.ui.act_chat.chat_preview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentChatsBinding
import com.example.chatapp.domain.model.Group
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject

class ChatsFragment : Fragment() {

    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: FirebaseFirestore
    private lateinit var listGroup: ArrayList<Group>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatsBinding.inflate(inflater)
        listGroup = arrayListOf()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventChangeListener()

        val adapter = GroupAdapter(listGroup)
        binding.groupRecyclerview.adapter = adapter
        binding.groupRecyclerview.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("/groups")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("Firestore Error: ", error.toString())
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        listGroup.add(dc.document.toObject(Group::class.java))
                    }
                }
            }
    }

}