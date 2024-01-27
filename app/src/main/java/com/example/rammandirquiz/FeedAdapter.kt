package com.example.rammandirquiz

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide


class FeedAdapter( private val context: Context, private val feed:List<FeedData>) :RecyclerView.Adapter<FeedAdapter.ViewHolder>(){


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val text:TextView= itemView.findViewById(R.id.feed_txt)
        val img:ImageView= itemView.findViewById(R.id.feed_img)
        val share:ImageView=itemView.findViewById(R.id.feed_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.feed_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return feed.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val feeds= feed[position]
        holder.text.text=feeds.text

        Glide.with(context).load(feeds.img).into(holder.img)

        holder.share.setOnClickListener{
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.setType("text/plain")
            val shareBody = feeds.text
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Ram Mandir Quiz ")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }

    }
}
