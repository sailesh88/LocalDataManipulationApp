package com.test.localdataapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.test.localdataapp.models.UserData
import java.util.*

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UsersViewHolder>() {

    private var data: List<UserData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_user_card, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: List<UserData>) {
        this.data = data
        notifyDataSetChanged()
    }

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val description: TextView = itemView.findViewById(R.id.tv_desc)
        private val userId: TextView = itemView.findViewById(R.id.tv_id)


        @SuppressLint("DefaultLocale")
        fun bind(item: UserData) = with(itemView) {
            val titleText: String = item.title[0].toUpperCase() + item.title.substring(1)
            title.text = titleText
            description.text = item.body
            userId.text = item.id.toString()

            setOnClickListener {
                Toast.makeText(
                        itemView.context,
                        "Item is clecked ${item.id}",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}