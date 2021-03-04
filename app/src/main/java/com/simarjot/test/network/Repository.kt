package com.simarjot.test.network

import android.os.Bundle
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.simarjot.test.network.model.FacebookUserData
import org.json.JSONException
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object Repository {
    private val service = UserService.create()

    suspend fun getFacebookData() = suspendCoroutine<FacebookUserData?> { continuation ->
        val request = GraphRequest.newMeRequest(
            AccessToken.getCurrentAccessToken()
        ) { obj, _ ->
            try {
                val id = obj.getString("id")
                val name = obj.getString("first_name")
                val imageUrl = "http://graph.facebook.com/$id/picture?type=large"
                var email: String? = null
                if (obj.has("email")) {
                    email = obj.getString("email")
                }
                val user = FacebookUserData(name, id, email ?: "abc@example.com", imageUrl)
                continuation.resume(user)
            } catch (e: JSONException) {
                continuation.resume(null)
            } catch (e: Exception) {
                continuation.resume(null)
            }
        }
        val parameters = Bundle()
        parameters.putString(
            "fields",
            "id,first_name,last_name,email,gender,birthday"
        )
        request.parameters = parameters
        request.executeAsync()
    }

    fun getUserList() = Pager(
        config = PagingConfig(
            pageSize = 6,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            UserPagingSource(service)
        }
    ).liveData
}