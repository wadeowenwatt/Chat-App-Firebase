package com.example.chatapp.domain.model

data class Group(
    val createdAt : String,
    val createdBy : String,
    val id : String,
    val members : ArrayList<String>? = null,
    val name: String? = null,
    val recentMessage: Map<*, *>,
    val type: String
)
