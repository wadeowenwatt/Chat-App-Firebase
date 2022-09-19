package com.example.chatapp.domain.model

data class Message(
    val id: String? = null,
    val groupId: String? = null,
    val sentAt: String? = null,
    val sentBy: String? = null,
    val text: String? = null
)
