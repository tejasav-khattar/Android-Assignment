package com.example.assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.models.PhotoModel
import kotlinx.android.synthetic.main.image_card_layout.view.*

class ImageAdapter(val items : List<PhotoModel>,val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(items.get(position).url)
            .into(holder.imageView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.image_card_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size;
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val imageView = view.ImageView
}