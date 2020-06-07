package com.naval.todonotesapp.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ServiceGenerator {

    companion object{

        val BASE_URL = "http://www.mocky.io/v2/5926ce9d11000096006ccb30/"
        val builder:Retrofit.Builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        val retorfit = builder.build()

        val httpClient = OkHttpClient.Builder()
        fun createService(serviceClass: Class<BlogService>): BlogService {
            return retorfit.create(serviceClass)
        }
    }


}