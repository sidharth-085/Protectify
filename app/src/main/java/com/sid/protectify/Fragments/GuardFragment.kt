package com.sid.protectify.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sid.protectify.Adapters.InviteAdapter
import com.sid.protectify.Adapters.MailAdapter
import com.sid.protectify.databinding.FragmentGuardBinding

class GuardFragment : Fragment(), MailAdapter.OnActionClick {
    private lateinit var binding: FragmentGuardBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mailAdapter: MailAdapter
    private lateinit var mContext: Context
    private lateinit var senderMail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuardBinding.inflate(inflater, container, false)
        firestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        senderMail = firebaseAuth.currentUser?.email.toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.guardCardView.setOnClickListener {
            Toast.makeText(mContext, "This Feature is not implemented yet !!", Toast.LENGTH_SHORT).show()
        }

        binding.sosCardView.setOnClickListener {
            Toast.makeText(mContext, "This Feature is not implemented yet !!", Toast.LENGTH_SHORT).show()
        }

        binding.sendInvite.setOnClickListener {
            sendInvite()
        }

        getInvites()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
    }

    private fun getInvites() {
        val currentUserEmail = firebaseAuth.currentUser?.email.toString()
        firestore.collection("users")
            .document(currentUserEmail)
            .collection("invites")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val listOfEmails: ArrayList<String> = ArrayList()
                    for (document in it.result) {
                        if (document.get("invite_status") == 0L) {
                            listOfEmails.add(document.id)
                        }
                    }

                    mailAdapter = MailAdapter(listOfEmails, this)
                    binding.mailRecyclerView.layoutManager = LinearLayoutManager(mContext)
                    binding.mailRecyclerView.adapter = mailAdapter
                }
            }
    }

    private fun sendInvite() {
        val mail = binding.emailInput.text.toString()

        val data = hashMapOf(
            "invite_status" to "0"
        )

        firestore.collection("users")
            .document(mail)
            .collection("invites")
            .document(senderMail)
            .set(data)
            .addOnSuccessListener {
                showToast("Invite Sent")
            }
            .addOnFailureListener {
                showToast("Error!! Invite not sent")
            }
    }

    override fun onAcceptClick(mail: String) {
        Log.d("Accepted Invite", "Accepted Invite from: $mail")

        val data = hashMapOf(
            "invite_status" to "1"
        )

        firestore.collection("users")
            .document(senderMail)
            .collection("invites")
            .document(mail)
            .set(data)
            .addOnSuccessListener {
                showToast("Invite Accepted!!")
            }
            .addOnFailureListener {
                showToast("Error!!")
            }
    }

    override fun onDenyClick(mail: String) {
        Log.d("Denied Invite", "Denied Invite from: $mail")

        val data = hashMapOf(
            "invite_status" to "-1"
        )

        firestore.collection("users")
            .document(senderMail)
            .collection("invites")
            .document(mail)
            .set(data)
            .addOnSuccessListener {
                showToast("Invite Accepted!!")
            }
            .addOnFailureListener {
                showToast("Error!!")
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = GuardFragment()
    }
}