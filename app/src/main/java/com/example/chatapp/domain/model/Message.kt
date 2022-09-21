package com.example.chatapp.domain.model

data class Message(
    val groupId: String? = null,
    val ownerId: String? = null,
    val name: String? = null,
    val sentAt: String? = null,
    val sentBy: String? = null,
    val text: String? = null
)
