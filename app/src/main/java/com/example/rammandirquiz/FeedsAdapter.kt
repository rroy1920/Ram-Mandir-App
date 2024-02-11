package com.example.rammandirquiz

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FeedsAdapter(private val context: Context, private val feed: MutableList<FeedsData>, private val tts: TextToSpeech) : RecyclerView.Adapter<FeedsAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val text: TextView = itemView.findViewById(R.id.feeds_txt)
        val img: ImageView = itemView.findViewById(R.id.feeds_img)
        val share: ImageView = itemView.findViewById(R.id.feeds_share)
        val title: TextView = itemView.findViewById(R.id.Feeds_Heading)
        val speaker: ImageButton = itemView.findViewById(R.id.speaker)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.feeds_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return  feed.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feeds= feed[position]
        holder.text.text=feeds.text
        holder.title.text=feeds.title

        holder.speaker.setOnClickListener {
            val spText=holder.text.text
            tts!!.speak(spText,TextToSpeech.QUEUE_FLUSH,null,null)
        }



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



