package com.simarjot.test.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.simarjot.test.network.Repository
import com.simarjot.test.network.model.User

class HomeViewModel : ViewModel() {
    val selectedUsers = mutableListOf<User>()

    val faceBookUserData = liveData {
        val userData = Repository.getFacebookData()
        emit(userData)
    }

    val users = Repository.getUserList().cachedIn(viewModelScope)
}