package com.example.chatapp.ui.act_chat.contact

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.config.Resource
import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.use_case.ContactUseCase
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel() {

    private var _listUser = MutableLiveData<ArrayList<User>>(arrayListOf())
    var listUser : LiveData<ArrayList<User>> = _listUser

    private var _contactUiState = MutableStateFlow(ContactUiState())
    var contactUiState : StateFlow<ContactUiState> = _contactUiState

    private lateinit var db : FirebaseFirestore

    private val contactUseCase = ContactUseCase(FirebaseFirestore.getInstance())

    init {
//        viewModelScope.launch {
//            getData()
//        }
//        eventChangeListener()
    }

//    private fun getData() {
//        contactUseCase().onEach { result ->
//            when(result) {
//                is Resource.Success -> {
//                    _contactUiState.value = ContactUiState(listUser = result.data ?: arrayListOf())
//                }
//                is Resource.Loading -> {
//                    Log.e("State", "Loading")
//                }
//                is Resource.Error -> {
//                    Log.e("State", "Error")
//                }
//            }
//        }
//    }

     // Cai nay get data static
//    private fun getDataUser(): ArrayList<User> {
//        var x = ArrayList<User>()
//        db = FirebaseFirestore.getInstance()
//        db.collection("/users")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    x.add(document.toObject(User::class.java))
//                }
//                _listUser.value!!.addAll(x)
//                Log.e("list data", _listUser.value.toString())
//            }
//            .addOnFailureListener { exception ->
//                Log.d("Show Data", "Error getting documents: ", exception)
//            }
//        return x
//    }

    private fun modifiedData(oldUser: User, newUser: User) {
        if (oldUser.firstName != newUser.firstName) {
            oldUser.firstName = newUser.firstName
        }
    }

    // get data realtime
    suspend fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("/users")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        Log.e("Firestore Error: ", error.message.toString())
                        return
                    }
                    var x = ArrayList<User>()
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        Log.e("typeDataChange", dc.type.toString())
                        x.add(dc.document.toObject(User::class.java))

//                        when (dc.type) {
//                            DocumentChange.Type.ADDED -> x.add(dc.document.toObject(User::class.java))
//                            DocumentChange.Type.REMOVED -> {
//                                x.forEach { user ->
//                                    if (dc.document.toObject(User::class.java).uid == user.uid) {
//                                        x.remove(user)
//                                    }
//                                }
//                            }
////                            DocumentChange.Type.MODIFIED -> {
////                                for (i in x.indices) {
////                                    if (dc.document.toObject(User::class.java).uid == x[i].uid) {
////                                        x[i] = dc.document.toObject(User::class.java)
////                                    }
////                                }
////                            }
//                            else -> {}
//                        }

                    }
                    _listUser.value!!.addAll(x)
                }
            })
    }
}