package com.naval.todonotesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naval.todonotesapp.R
import com.naval.todonotesapp.clicklisteners.ItemClickListener
import com.naval.todonotesapp.model.Blog

class BlogsAdapter : RecyclerView.Adapter<BlogsAdapter.ViewHolder> {

    private  val blogsList: List<Blog>


    constructor(blogsList: List<Blog>):super() {
        this.blogsList = blogsList

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blog_adapter_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return blogsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blog = blogsList[position]
        Glide.with(holder.itemView).load(blog.img_url).into(holder.imageViewBlogAdapter)
        holder.blogAdapterTextViewTitle.text = blog.title
        holder.blogAdapterTextViewDescription.text = blog.description
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var blogAdapterTextViewTitle:TextView = itemView.findViewById(R.id.blogAdapterTextViewTitle)
        var blogAdapterTextViewDescription:TextView = itemView.findViewById(R.id.blogAdapterTextViewDescription)
        var imageViewBlogAdapter:ImageView = itemView.findViewById(R.id.imageViewBlogAdapter)

    }
}