package com.simarjot.test.network

import android.util.Log
import androidx.paging.PagingSource
import com.simarjot.test.network.model.User

class UserPagingSource(
    private val userService: UserService
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val currentPosition = params.key ?: 0
        return try {
            val res = userService.getUsers(currentPosition)
            val users = res.data
            Log.d("kalsi", users.toString())
            LoadResult.Page(
                data = users,
                prevKey = if (currentPosition == 0) null else currentPosition - 1,
                nextKey = if (users.isEmpty()) null else currentPosition + 1
            )
        } catch (ex: Exception) {
            Log.d("kalsi", "load: ${ex.localizedMessage}", ex)
            LoadResult.Error(ex)
        }
    }
}