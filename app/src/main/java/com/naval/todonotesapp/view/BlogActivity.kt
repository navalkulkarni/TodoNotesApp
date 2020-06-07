package com.naval.todonotesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.naval.todonotesapp.R
import com.naval.todonotesapp.adapter.BlogsAdapter
import com.naval.todonotesapp.model.JsonResponse
import com.naval.todonotesapp.retrofit.BlogService
import com.naval.todonotesapp.retrofit.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogActivity : AppCompatActivity() {

    lateinit var recyclerView:RecyclerView
    val TAG = "BlogLog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        bindView()
        getBlogs()
        //getBlogsFromRetrofit()
    }



    private fun getBlogsFromRetrofit() {
       val service= ServiceGenerator.createService(BlogService::class.java)
        val call = service.getBlogs()
        call.enqueue(object: Callback<JsonResponse>{

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                Log.d(TAG,"Error :"+t.message)
            }

            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
                if(response.isSuccessful)
                {
                    setupRecyclerView(response = response.body())
                }
            }
        })
    }

    private fun getBlogs() {
        AndroidNetworking.get("http://www.mocky.io/v2/5926ce9d11000096006ccb30")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(JsonResponse::class.java,object:ParsedRequestListener<JsonResponse>{
                    override fun onResponse(response: JsonResponse?) {

                        setupRecyclerView(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.d(TAG,anError?.localizedMessage)
                    }

                })
    }

    private fun bindView() {
        recyclerView = findViewById(R.id.recyclerViewBlogs)
    }

    private fun setupRecyclerView(response: JsonResponse?)
    {
        val blogsAdapter = BlogsAdapter(response!!.data)
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = blogsAdapter
    }
}