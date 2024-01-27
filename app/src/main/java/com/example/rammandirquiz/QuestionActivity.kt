package com.example.rammandirquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rammandirquiz.databinding.ActivityQuestionBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlin.properties.Delegates


private lateinit var binding: ActivityQuestionBinding

private lateinit var optionadapter :OptionAdapter
private lateinit var recyclerView:RecyclerView
var quizzes:MutableList<QuizSubCat>? =null
var questions: MutableMap<String,Questions>? = null
var index by Delegates.notNull<Int>()

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        index=1
        setupFireStore()
        setUpEventListner()
    }

    private fun setUpEventListner() {


        binding.btnPre.setOnClickListener {
            index -= 1
            bindViews()
        }
        binding.btnNext.setOnClickListener{
            index += 1
            bindViews()
        }
        binding.btnSub.setOnClickListener{
            val intent = Intent(this,ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("Quiz",json)
            startActivity(intent)
            finish()


        }
    }

    private fun setupFireStore() {
         val  firestore= FirebaseFirestore.getInstance()
        val id=intent.getStringExtra("id")
        var title= intent.getStringExtra("title")
        binding.titleBar.text=title
        val collectionReference = firestore.collection("quizzes").whereEqualTo("id",id).get()
            .addOnSuccessListener{


                    quizzes= it.toObjects(QuizSubCat::class.java)
                    questions= quizzes!![0].questions
                    bindViews()

        }
    }

    private fun bindViews() {

        binding.btnNext.visibility=View.GONE
        binding.btnPre.visibility=View.GONE
        binding.btnSub.visibility=View.GONE

        if (index == 1){
            binding.btnNext.visibility=View.VISIBLE
        }else if (index == questions!!.size){
            binding.btnSub.visibility=View.VISIBLE
            binding.btnPre.visibility=View.VISIBLE
        }
        else{
            binding.btnPre.visibility=View.VISIBLE
            binding.btnNext.visibility=View.VISIBLE
        }

        val question= questions!!["q$index"]


        question?.let {
            findViewById<TextView>(R.id.desc).text=it.desc
            optionadapter =OptionAdapter(this,it)
            recyclerView= findViewById(R.id.optionList)
            recyclerView.layoutManager=LinearLayoutManager(this)
            recyclerView.adapter=optionadapter
            recyclerView.setHasFixedSize(true)
        }


    }
}