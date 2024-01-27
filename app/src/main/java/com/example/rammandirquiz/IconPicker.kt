package com.example.rammandirquiz

object IconPicker {

    val icons = arrayOf(

        R.drawable.ram_final,
        R.drawable.book,
        R.drawable.rama,
        R.drawable.diya,
        R.drawable.puja,
        R.drawable.gada,
        R.drawable.hanuman,
        R.drawable.kalasha,
        R.drawable.hindu,
        R.drawable.kalasha,
        R.drawable.swastika,
        R.drawable.temple,
        R.drawable.vishnu,
    )


    var currentIcon=0

       fun getIcon(): Int{
        currentIcon=(currentIcon+1)% icons.size
        return icons[currentIcon]
    }
}