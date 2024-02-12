package com.example.rammandirquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.combine


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


private lateinit var adapter:FeedAdapter
private lateinit var recyclerView: RecyclerView
private lateinit var feedarray :List<FeedData>


class HomeFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_frag, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        firestore= FirebaseFirestore.getInstance()
        firestore.collection("feed").
        addSnapshotListener{
                value,error ->

            val data = value?.toObjects(FeedData::class.java)


            val layoutManager= LinearLayoutManager(context)
            layoutManager.reverseLayout
            layoutManager.stackFromEnd
            recyclerView= view.findViewById(R.id.home_rv)
            recyclerView.layoutManager=layoutManager
            adapter = data?.let { FeedAdapter(requireContext(), it) }!!

            val admobNativeAdAdapter= AdmobNativeAdAdapter.Builder.with("/6499/example/native",
                adapter, "medium"
            ).adItemInterval(5).build()
            recyclerView.adapter = admobNativeAdAdapter

        }

    }


}