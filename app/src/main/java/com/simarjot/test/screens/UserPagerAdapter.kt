package com.simarjot.test.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simarjot.test.R
import com.simarjot.test.network.model.User

class UserPagerAdapter(private val add: (user: User) -> Unit) :
    PagingDataAdapter<User, UserViewHolder>(diffCallBack) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        item ?: return
        Glide.with(holder.itemView.context).load(item.avatar).into(holder.imageView)
        holder.name.text = "${item.firstName} ${item.lastName}"
        holder.checkBox.setOnCheckedChangeListener { _, _ ->
            add(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder.create(parent)

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.image)
    val name: TextView = view.findViewById(R.id.name)
    val checkBox: CheckBox = view.findViewById(R.id.checkBox)

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_user, parent, false)
            return UserViewHolder(view)
        }
    }
}