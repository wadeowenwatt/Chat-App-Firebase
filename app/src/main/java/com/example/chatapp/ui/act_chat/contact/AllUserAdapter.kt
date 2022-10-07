package com.example.chatapp.ui.act_chat.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.domain.model.User


//class AllUserAdapter(private val listUser: ArrayList<User>) : RecyclerView.Adapter<AllUserAdapter.AllUserViewHolder>() {
class AllUserAdapter :
    ListAdapter<User, AllUserAdapter.AllUserViewHolder>(DiffCallBack) {

    class AllUserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val avatarImage: ImageView =
            itemView.findViewById(R.id.avatar_user_item)
        val nameUser: TextView = itemView.findViewById(R.id.name_item)
        val statusString: TextView = itemView.findViewById(R.id.status_title)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllUserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return AllUserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AllUserViewHolder, position: Int) {
        val element = getItem(position)
        holder.avatarImage.setImageResource(R.drawable.avatar)
        holder.nameUser.text = element.firstName + " " + element.lastName
        holder.statusString.text = "Online"
    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem.uid === newItem.uid
            }

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem.uid == newItem.uid
                        && oldItem.firstName == newItem.firstName
                        && oldItem.lastName == newItem.lastName
            }

        }
    }

}
