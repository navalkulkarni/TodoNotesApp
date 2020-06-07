package com.naval.todonotesapp.retrofit

import com.naval.todonotesapp.model.Blog
import com.naval.todonotesapp.model.JsonResponse
import retrofit2.Call
import retrofit2.http.GET

interface BlogService {
    @GET("http://www.mocky.io/v2/5926ce9d11000096006ccb30/")
    fun getBlogs() : Call<JsonResponse>
}