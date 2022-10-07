package com.example.chatapp.domain.use_case

import android.util.Log
import com.example.chatapp.config.Resource
import com.example.chatapp.domain.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ContactUseCase(private val db: FirebaseFirestore) {

    operator fun invoke(): Flow<Resource<ArrayList<User>>> = flow {
        try {
            emit(Resource.Loading())

            val listUser = arrayListOf<User>()
            db.collection("/user")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        listUser.add(document.toObject(User::class.java))
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("Show Data", "Error getting documents: ", exception)
                }

            emit(Resource.Success(listUser))
        } catch (e: IOException) {

        }
    }
}