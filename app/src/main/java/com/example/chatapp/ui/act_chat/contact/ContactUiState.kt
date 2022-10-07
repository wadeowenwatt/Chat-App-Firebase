package com.example.chatapp.ui.act_chat.contact

import com.example.chatapp.domain.model.User

data class ContactUiState(
    var listUser: ArrayList<User> = arrayListOf(),
    var error: String = ""
)