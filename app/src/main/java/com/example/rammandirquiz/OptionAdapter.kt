package com.example.rammandirquiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class OptionAdapter (val context: Context, val question:Questions):RecyclerView.Adapter<OptionAdapter.OptionViewHolder>(){



    inner class OptionViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        var optionView =itemView.findViewById<TextView>(R.id.quiz_option)


    }

     private var option:List<String> = listOf(question.op1,question.op2,question.op3,question.op4)




     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
         val view=LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
         return OptionViewHolder(view)
     }

     override fun getItemCount(): Int {
         return option.size
     }

     override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
         holder.optionView.text =option[position]
         holder.itemView.setOnClickListener{

             question.userAns = option[position]
             notifyDataSetChanged()


         }

         if (question.userAns == option[position]){
             holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)

         }else{
             holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
         }
     }
 }