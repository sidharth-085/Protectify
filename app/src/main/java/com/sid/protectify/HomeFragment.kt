package com.sid.protectify

import android.os.Bundle
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

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.card_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}