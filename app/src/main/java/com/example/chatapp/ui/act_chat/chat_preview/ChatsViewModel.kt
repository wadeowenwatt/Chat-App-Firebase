package com.example.chatapp.ui.act_chat.chat_preview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.domain.model.Message
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class ChatsViewModel : ViewModel() {

    private var _listGroup = MutableLiveData<ArrayList<Message>>(arrayListOf())
    var listGroup: LiveData<ArrayList<Message>> = _listGroup

    private lateinit var db: FirebaseFirestore

    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("/message")
            .addSnapshotListener { value, e ->
                when {
                    e != null -> Log.e("Firestore error: ", e.toString())
                    value != null -> {
                        for (dc: DocumentChange in value?.documentChanges!!) {
                            if (dc.type == DocumentChange.Type.ADDED) {
                                _listGroup.value!!.add(
                                    dc.document.toObject(
                                        Message::class.java
                                    )
                                )
                            }
                        }
                    }
                }
            }
    }
}