package com.example.chatapp.ui.act_chat.contact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.databinding.FragmentContactBinding
import com.example.chatapp.domain.model.User
import com.google.firebase.firestore.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    private val contactViewModel: ContactViewModel by activityViewModels()

    private lateinit var listUser: ArrayList<User>
    private lateinit var adapter: AllUserAdapter
    private lateinit var db: FirebaseFirestore

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

        adapter = AllUserAdapter()
        binding.listUserRecyclerview.adapter = adapter
        // Quan ly flow listUser loading data.
//        contactViewModel.listUser.observe(viewLifecycleOwner) {
//            Log.e("data fg", it.toString())
//            adapter.submitList(it)
//            binding.listUserRecyclerview.layoutManager = LinearLayoutManager(requireContext())
//        }

        lifecycleScope.launch(Dispatchers.IO) {
            contactViewModel.eventChangeListener()
            withContext(Dispatchers.Main) {
                contactViewModel.listUser.observe(viewLifecycleOwner) {
                    Log.e("data fg", it.toString())
                    adapter.submitList(it)
                    binding.listUserRecyclerview.layoutManager =
                        LinearLayoutManager(requireContext())
                }
            }
        }

    }

}