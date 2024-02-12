package com.example.rammandirquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var adapter:Category_Aadapter
private lateinit var recyclerView:RecyclerView
private lateinit var quizArrayList :ArrayList<QuizCatData>
lateinit var firestore: FirebaseFirestore
lateinit var imageId:Array<Int>

lateinit var quizCat :Array<String>

class QuizFrag : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        firestore= FirebaseFirestore.getInstance()
        firestore.collection("quizzes").addSnapshotListener{
                value,error ->

            quizArrayList= arrayListOf()
            val data = value?.toObjects(QuizCatData::class.java)
            quizArrayList.addAll(data!!)

            val layoutManager=LinearLayoutManager(context)
            recyclerView= view.findViewById(R.id.quiz_item_rv)
            recyclerView.layoutManager=layoutManager
            recyclerView.setHasFixedSize(true)
            adapter = Category_Aadapter(requireContext(),quizArrayList)

            val admobNativeAdAdapter= AdmobNativeAdAdapter.Builder.with("/6499/example/native",
                adapter, "small"
            ).adItemInterval(5).build()
            recyclerView.adapter= admobNativeAdAdapter

        }

    }




    }



