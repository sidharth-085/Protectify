package com.sid.protectify.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sid.protectify.databinding.ItemInviteRequestBinding

class MailAdapter(
    private val listOfEmails: List<String>,
    private val onActionClick: OnActionClick
): RecyclerView.Adapter<MailAdapter.MailAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = ItemInviteRequestBinding.inflate(inflater, parent, false)
        return MailAdapterViewHolder(item)
    }

    override fun getItemCount(): Int {
        return listOfEmails.size
    }

    override fun onBindViewHolder(holder: MailAdapterViewHolder, position: Int) {
        val item = listOfEmails[position]
        holder.inviteMail.text = item

        holder.acceptText.setOnClickListener {
            onActionClick.onAcceptClick(item)
        }

        holder.denyText.setOnClickListener {
            onActionClick.onDenyClick(item)
        }
    }

    class MailAdapterViewHolder(binding: ItemInviteRequestBinding): RecyclerView.ViewHolder(binding.root) {
        val inviteMail = binding.mailText
        val acceptText = binding.acceptText
        val denyText = binding.denyText
    }

    interface OnActionClick {
        fun onAcceptClick(mail: String)
        fun onDenyClick(mail: String)
    }
}