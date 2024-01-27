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
import com.example.rammandirquiz.databinding.QuizCategoryItemBinding



class Category_Aadapter(val context: Context, private var catList:ArrayList<QuizCatData>):
    RecyclerView.Adapter<Category_Aadapter.ViewHolder>() {






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quiz_category_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val currentItem =catList[position]
        holder.title.text =currentItem.title.toString()

        holder.Image.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener{

            val intent = Intent(context,QuestionActivity::class.java)

            intent.putExtra("title",catList[position].title)
            intent.putExtra("id",catList[position].id)
            context.startActivity(intent)


        }
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val Image: ImageView= itemView.findViewById(R.id.cat_image)
        val title:TextView = itemView.findViewById(R.id.cat_name)

    }
}