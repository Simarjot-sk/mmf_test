package com.simarjot.test.screens

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.simarjot.test.R

class UserListFragment : Fragment(R.layout.fragment_user_list) {
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.user_rv)
        button = view.findViewById(R.id.filter_result_button)

        val adapter = UserPagerAdapter { selectedUser ->
            //currently only adding users to list.
            //will add code to remove from list later some time.
            homeViewModel.selectedUsers.add(selectedUser)
        }
        recyclerView.adapter = adapter

        homeViewModel.users.observe(viewLifecycleOwner) { userPagingData ->
            adapter.submitData(lifecycle, userPagingData)
        }

        button.setOnClickListener {
            findNavController().navigate(R.id.filteredUsersFragment)
        }
    }
}