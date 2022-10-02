package com.example.chatapp.domain.model

data class User(
    var uid: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var groupId: ArrayList<String>? = null,
    var photoUrl: String? = null
)