package com.sid.protectify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(private val listOfCardItems: List<CardItemModel>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.card_item, parent, false)

        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfCardItems[position]

        holder.userName.text = item.name
        holder.userBattery.text = item.battery
        holder.userDistance.text = item.distance
        holder.userAddress.text = item.address
    }

    override fun getItemCount(): Int {
        return listOfCardItems.size
    }

    class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.findViewById<ImageView>(R.id.user_image)
        val userName = itemView.findViewById<TextView>(R.id.user_name)
        val userBattery = itemView.findViewById<TextView>(R.id.user_battery)
        val userDistance = itemView.findViewById<TextView>(R.id.user_distance)
        val userAddress = itemView.findViewById<TextView>(R.id.user_address)
    }
}