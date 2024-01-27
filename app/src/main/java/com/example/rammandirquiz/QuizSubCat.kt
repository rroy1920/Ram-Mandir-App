package com.example.rammandirquiz

data class QuizSubCat
    (

    var id: String = "",
     var title:String= "",
     var questions:MutableMap<String,Questions> = mutableMapOf()


)
