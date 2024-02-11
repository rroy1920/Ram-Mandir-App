package com.example.rammandirquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.rammandirquiz.databinding.ActivityDetailBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

private lateinit var binding: ActivityDetailBinding

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        val mAdView=findViewById<AdView>(R.id.adView)

        MobileAds.initialize(this){}

        val adRequest: AdRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val url= intent.getStringExtra("URL")
        if (url != null){
            binding.webView.settings.javaScriptEnabled=true
            binding.webView.settings.userAgentString="Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"
            binding.webView.webViewClient= object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressBar.visibility= View.GONE
                    binding.webView.visibility=View.VISIBLE
                }
            }
            binding.webView.loadUrl(url)

        }
    }
}