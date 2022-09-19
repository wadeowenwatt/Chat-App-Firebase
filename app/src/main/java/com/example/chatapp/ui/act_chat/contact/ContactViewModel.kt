package com.example.chatapp.ui.act_chat.contact

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.domain.model.User
import com.google.firebase.firestore.*

class ContactViewModel : ViewModel() {

    private var _listUser = MutableLiveData<ArrayList<User>>(arrayListOf())
    var listUser : LiveData<ArrayList<User>> = _listUser

    private lateinit var db : FirebaseFirestore

    init {
        eventChangeListener()
    }

    private fun eventChangeListener() {
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

                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            _listUser.value!!.add(dc.document.toObject(User::class.java))
                        }
                    }
                }
            })
    }

}