package com.example.rammandirquiz

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.rammandirquiz.databinding.ActivityResultBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    lateinit var quiz:QuizSubCat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()

        val mAdView=findViewById<AdView>(R.id.adView)

        MobileAds.initialize(this){}

        val adRequest: AdRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun setUpViews() {
        val quizData= intent.getStringExtra("Quiz")
        quiz=Gson().fromJson<QuizSubCat>(quizData,QuizSubCat::class.java)
        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {

        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.desc}</b></font><br/>")
            builder.append("<font color'#FF0000'><b>Your Answer: ${question.userAns}</b></font><br/>")
            builder.append("<font color='#009688'>Answer: ${question.ans}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
           binding.txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            binding.txtAnswer.text = Html.fromHtml(builder.toString());
        }

    }

    private fun calculateScore() {
        var score=0
        for(entry in quiz.questions.entries){
            val questions=entry.value
            if (questions.ans == questions.userAns){
                score+=10
            }
        }
        binding.txtScore.text= "Your Score : $score"
    }
}