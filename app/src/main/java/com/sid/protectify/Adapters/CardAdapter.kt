package com.sid.protectify.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sid.protectify.Models.CardItemModel
import com.sid.protectify.databinding.CardItemBinding

class CardAdapter(private val listOfCardItems: List<CardItemModel>) : RecyclerView.Adapter<CardAdapter.CardAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = CardItemBinding.inflate(inflater, parent, false)

        return CardAdapterViewHolder(item)
    }

    override fun onBindViewHolder(holder: CardAdapterViewHolder, position: Int) {
        val item = listOfCardItems[position]

        holder.userName.text = item.name
        holder.userBattery.text = item.battery
        holder.userDistance.text = item.distance
        holder.userAddress.text = item.address
    }

    override fun getItemCount(): Int {
        return listOfCardItems.size
    }

    class CardAdapterViewHolder(cardItemBinding: CardItemBinding): RecyclerView.ViewHolder(cardItemBinding.root) {
        val userImage = cardItemBinding.userImage
        val userName = cardItemBinding.userName
        val userBattery = cardItemBinding.userBattery
        val userDistance = cardItemBinding.userDistance
        val userAddress = cardItemBinding.userAddress
    }
}