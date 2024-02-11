package com.example.rammandirquiz

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter (val context: Context, val articles:List<Article> ): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var img = itemView.findViewById<ImageView>(R.id.articleImage)
        var title = itemView.findViewById<TextView>(R.id.articleTitle)
        var desc = itemView.findViewById<TextView>(R.id.articleDescription)
        var source = itemView.findViewById<TextView>(R.id.source)
        var date = itemView.findViewById<TextView>(R.id.date)
        var share = itemView.findViewById<ImageButton>(R.id.shareBtn)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.news_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article=articles[position]

        holder.title.text=article.title
        holder.desc.text=article.description
        holder.source.text=article.source.toString()
        holder.date.text=article.publishedAt
        Glide.with(context).load(article.urlToImage).into(holder.img)

        holder.itemView.setOnClickListener{
            val intent= Intent(context,Detail::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }

        holder.share.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.setType("text/plain")
            val shareBody =article.title
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Ram Mandir Quiz ")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }

    }
}