package com.sid.protectify.Fragments

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.sid.protectify.Adapters.CardAdapter
import com.sid.protectify.Adapters.InviteAdapter
import com.sid.protectify.Database.ContactDatabase
import com.sid.protectify.Models.CardItemModel
import com.sid.protectify.Models.ContactItemModel
import com.sid.protectify.Constants.PrefConstants
import com.sid.protectify.Constants.SharedPref
import com.sid.protectify.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var inviteAdapter: InviteAdapter
    private lateinit var cardAdapter: CardAdapter
    lateinit var binding: FragmentHomeBinding

    private val listOfContacts: ArrayList<ContactItemModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
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

        cardAdapter = CardAdapter(listOfCardItems)

        val cardRecyclerView = binding.cardRecyclerView
        cardRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cardRecyclerView.adapter = cardAdapter

        inviteAdapter = InviteAdapter(listOfContacts)
        fetchDatabaseContacts()

        CoroutineScope(Dispatchers.IO).launch {
            insertDatabaseContacts(fetchContacts())
        }

        val contactRecyclerView = binding.inviteRecyclerView
        contactRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        contactRecyclerView.adapter = inviteAdapter

        val threeDotsButton = binding.threeDotsImage
        threeDotsButton.setOnClickListener {
            SharedPref.putBoolean(PrefConstants.IS_USER_LOGGED_IN, false)

            FirebaseAuth.getInstance().signOut()
        }
    }

    private fun fetchDatabaseContacts() {
        val database = ContactDatabase.getDatabase(requireContext())
        database.contactDao().getAllContacts().observe(viewLifecycleOwner) {
            listOfContacts.clear()
            listOfContacts.addAll(it)

            inviteAdapter.notifyDataSetChanged()
        }
    }

    private suspend fun insertDatabaseContacts(listOfContacts: ArrayList<ContactItemModel>) {
        val database = ContactDatabase.getDatabase(requireContext())
        database.contactDao().insertAllContacts(listOfContacts)
    }

    private fun fetchContacts(): ArrayList<ContactItemModel> {
        val cr = requireActivity().contentResolver
        val cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        val listContacts: ArrayList<ContactItemModel> = ArrayList()

        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
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
                        while (pCursor.moveToNext()) {
                            val phoneNumber = pCursor.getString(pCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            listContacts.add(ContactItemModel(name, phoneNumber))
                        }

                        pCursor.close()
                    }
                }
            }

            cursor.close()
        }

        return listContacts
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}