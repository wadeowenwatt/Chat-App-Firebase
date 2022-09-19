package com.example.chatapp.domain.model

data class Group(
    val groupId : String? = null,
    val member : ArrayList<String>? = null,
    val name: String? = null
)
