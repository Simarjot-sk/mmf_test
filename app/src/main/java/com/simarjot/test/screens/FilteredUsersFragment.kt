package com.simarjot.test.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.simarjot.test.R

class FilteredUsersFragment : Fragment(R.layout.fragment_filtered_users) {
    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.selected_user_rv)

        //reusing the same adapter here for saving time.
        val userPagerAdapter = UserPagerAdapter {}
        recyclerView.adapter = userPagerAdapter
        userPagerAdapter.submitData(lifecycle, PagingData.from(homeViewModel.selectedUsers))
    }
}