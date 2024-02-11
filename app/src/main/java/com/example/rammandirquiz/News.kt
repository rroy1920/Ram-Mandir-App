package com.example.rammandirquiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

 private lateinit var adapter: NewsAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [News.newInstance] factory method to
 * create an instance of this fragment.
 */
class News : Fragment() {
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
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdView=view.findViewById<AdView>(R.id.adView)

        MobileAds.initialize(requireContext()){}

        val adRequest: AdRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        getNews()
    }

    private fun getNews() {
        val news =NewsService.newsInstance.getHeadlines("Ram mandir ",1,"publishedAt")
        news.enqueue(object :retrofit2.Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {


                val news = response.body()
                if (news!=null){

                    Log.d("Ram",news.toString())
                    adapter= NewsAdapter(context!!,news.articles)
                    val recyclerView=view?.findViewById<RecyclerView>(R.id.recyclerHeadlines)

                    recyclerView?.layoutManager= LinearLayoutManager(context)
                    recyclerView?.adapter= adapter


                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                Log.d("Ram","error",t)
            }

        }

        )
    }
}
