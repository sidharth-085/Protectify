package com.sid.protectify.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sid.protectify.Models.ContactItemModel
import com.sid.protectify.databinding.InviteItemBinding

class InviteAdapter(private val listOfContacts: List<ContactItemModel>): RecyclerView.Adapter<InviteAdapter.InviteAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = InviteItemBinding.inflate(inflater, parent, false)
        return InviteAdapterViewHolder(item)
    }

    override fun onBindViewHolder(holder: InviteAdapterViewHolder, position: Int) {
        val item = listOfContacts[position]
        holder.contactName.text = item.name
    }

    override fun getItemCount(): Int {
        return listOfContacts.size
    }

    class InviteAdapterViewHolder(inviteItemBinding: InviteItemBinding) : RecyclerView.ViewHolder(inviteItemBinding.root) {
        val contactName = inviteItemBinding.txtName
    }
}