package com.simarjot.test.network

import com.simarjot.test.network.model.UserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): UserResponse

    companion object {
        fun create(): UserService = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }
}
