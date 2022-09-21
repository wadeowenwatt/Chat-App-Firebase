package com.example.chatapp.ui.act_chat.chat_preview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.domain.model.Group
import com.example.chatapp.domain.model.Message

class GroupAdapter(private val listGroup: ArrayList<Message>): RecyclerView.Adapter<GroupAdapter.GroupItemViewHolder>() {

    class GroupItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImage : ImageView = itemView.findViewById(R.id.avatar_user_item_chat)
        val nameUser : TextView = itemView.findViewById(R.id.name_item_chat)
        val previewChat : TextView = itemView.findViewById(R.id.review_chat)
        val onlineTime : TextView = itemView.findViewById(R.id.online_time)
        val unseenTime : TextView = itemView.findViewById(R.id.unseen_mess)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_chat, parent, false)
        return GroupItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GroupItemViewHolder, position: Int) {
        val element = listGroup[position]
        holder.avatarImage.setImageResource(R.drawable.avatar)
        holder.nameUser.text = element.name
        holder.previewChat.text = "How are you?"
        holder.onlineTime.text = "Today"
        holder.unseenTime.text = "1"
    }

    override fun getItemCount(): Int = listGroup.size
}