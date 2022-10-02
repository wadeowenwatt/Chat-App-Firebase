package com.example.chatapp.ui.act_chat.contact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.databinding.FragmentContactBinding
import com.example.chatapp.domain.model.User

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    private val contactViewModel: ContactViewModel by activityViewModels()

    private lateinit var listUser: ArrayList<User>

    private lateinit var adapter : AllUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater)
        listUser = arrayListOf()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        adapter = AllUserAdapter()
        // Quan ly flow listUser loading data.
        contactViewModel.listUser.observe(viewLifecycleOwner) {
//            adapter.submitList(listUser)
            val adapter = AllUserAdapter(it)
            binding.listUserRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            binding.listUserRecyclerview.adapter = adapter

            Log.e("test data: ", it.toString())
        }

    }

}