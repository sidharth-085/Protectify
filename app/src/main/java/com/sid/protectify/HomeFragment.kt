package com.sid.protectify

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfCardItems = listOf(
            CardItemModel(
                name = "Sidharth",
                battery = "80%",
                address = "9th buildind, 2nd floor, maldiv road, manali 9th buildind, 2nd floor 9th buildind, 2nd floor",
                distance = "100m"
            ),
            CardItemModel(
                name = "Sidharth",
                battery = "80%",
                address = "9th buildind, 2nd floor, maldiv road, manali 9th buildind, 2nd floor 9th buildind, 2nd floor",
                distance = "100m"
            ),
            CardItemModel(
                name = "Sidharth",
                battery = "80%",
                address = "9th buildind, 2nd floor, maldiv road, manali 9th buildind, 2nd floor 9th buildind, 2nd floor",
                distance = "100m"
            ),
            CardItemModel(
                name = "Sidharth",
                battery = "80%",
                address = "9th buildind, 2nd floor, maldiv road, manali 9th buildind, 2nd floor 9th buildind, 2nd floor",
                distance = "100m"
            )
        )

        val adapter = CardItemAdapter(listOfCardItems)

        val cardRecyclerView = requireView().findViewById<RecyclerView>(R.id.card_recycler_view)
        cardRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cardRecyclerView.adapter = adapter

        val listOfContacts = fetchContacts()

        val inviteAdapter = InviteAdapter(listOfContacts)
        val contactRecyclerView = requireView().findViewById<RecyclerView>(R.id.invite_recycler_view)
        contactRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        contactRecyclerView.adapter = inviteAdapter
    }

    private fun fetchContacts(): ArrayList<ContactItemModel> {
        val cr = requireActivity().contentResolver
        val cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        val listContacts: ArrayList<ContactItemModel> = ArrayList()

        if (cursor != null && cursor.count > 0) {
            while (cursor != null && cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                val hasPhoneNumber = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                if (hasPhoneNumber > 0) {
                    val pCursor = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id),
                        ""
                    )

                    if (pCursor != null && pCursor.count > 0) {
                        while (pCursor != null && pCursor.moveToNext()) {
                            val phoneNumber = pCursor.getString(pCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            listContacts.add(ContactItemModel(name, phoneNumber))
                        }

                        pCursor.close()
                    }
                }
            }

            if (cursor != null) {
                cursor.close()
            }
        }

        return listContacts
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}