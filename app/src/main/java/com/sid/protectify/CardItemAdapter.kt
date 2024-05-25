package com.sid.protectify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardItemAdapter(private val listOfCardItems: List<CardItemModel>) : RecyclerView.Adapter<CardItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.card_item, parent, false)

        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: CardItemAdapter.ViewHolder, position: Int) {
        val item = listOfCardItems[position]

        holder.userName.text = item.name
    }

    override fun getItemCount(): Int {
        return listOfCardItems.size
    }

    class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.findViewById<ImageView>(R.id.user_image)
        val userName = itemView.findViewById<TextView>(R.id.user_name)
    }
}