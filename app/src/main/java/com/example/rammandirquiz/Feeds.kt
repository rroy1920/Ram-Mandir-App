package com.example.rammandirquiz

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Locale


private lateinit var adapter:FeedsAdapter
private lateinit var recyclerView: RecyclerView
private lateinit var tts: TextToSpeech

/**
 * A simple [Fragment] subclass.
 * Use the [Feeds.newInstance] factory method to
 * create an instance of this fragment.
 */
class Feeds : Fragment(),TextToSpeech.OnInitListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tts= TextToSpeech(context,this)





        firestore= FirebaseFirestore.getInstance()
        firestore.collection("feeds").
        addSnapshotListener{
                value,error ->

            val data = value?.toObjects(FeedsData::class.java)


            val layoutManager= LinearLayoutManager(context)
            layoutManager.reverseLayout
            layoutManager.stackFromEnd
            recyclerView= view.findViewById(R.id.feeds_rv)
            recyclerView.layoutManager=layoutManager
            adapter = FeedsAdapter(requireContext(),data!!,tts)

            val admobNativeAdAdapter= AdmobNativeAdAdapter.Builder.with("/6499/example/native",
                adapter, "medium"
            ).adItemInterval(7).build()
            recyclerView.adapter= admobNativeAdAdapter

    }


}

    override fun onDestroy() {

        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            var output= tts!!.setLanguage(Locale.ENGLISH)

            if (output== TextToSpeech.LANG_MISSING_DATA || output == TextToSpeech.LANG_NOT_SUPPORTED){

            }else{

            }
        }else{

        }
    }
}