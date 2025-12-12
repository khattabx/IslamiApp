package com.android.islami.islamiproject.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BuildRetrofit {

    companion object{

        private var retrofit_object: Retrofit? = null


        private fun getInstanceRetrofit(url: String): Retrofit {

                retrofit_object = Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit_object!!

        }

        fun get_api(url: String): ApiServices {
            return getInstanceRetrofit(url).create(ApiServices::class.java)
        }

    }
}