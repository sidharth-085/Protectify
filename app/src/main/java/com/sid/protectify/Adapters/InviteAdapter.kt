package com.sid.protectify.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sid.protectify.Models.ContactItemModel
import com.sid.protectify.R

class InviteAdapter(private val listOfContacts: List<ContactItemModel>): RecyclerView.Adapter<InviteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.invite_item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfContacts[position]
        holder.contactName.text = item.name
    }

    override fun getItemCount(): Int {
        return listOfContacts.size
    }

    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactName = itemView.findViewById<TextView>(R.id.txt_name)
    }
}