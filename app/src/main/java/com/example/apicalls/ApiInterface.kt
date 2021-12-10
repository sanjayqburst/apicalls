package com.example.apicalls

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/users/")
    fun getData():Call<ProfileData>
}