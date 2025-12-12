package com.android.islami.islamiproject.Api

import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("radio_ar.json")
    fun get_radio(): Call<RadioResponse>

    @GET("quran")
    fun get_quran_audio(): Call<QuranResponse>
}