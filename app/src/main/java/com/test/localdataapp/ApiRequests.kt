package com.test.localdataapp

import com.test.localdataapp.models.UserData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface ApiRequests {

    // https://jsonplaceholder.typicode.com/posts
    // I'm using the json response from the above URl for the card details in the list view.
    @GET("posts")
    fun getUsers(): Call<List<UserData>>
}

object ApiServices{
    val apiRequests: ApiRequests

    init {
        val api: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiRequests = api.create(ApiRequests::class.java)
    }


}