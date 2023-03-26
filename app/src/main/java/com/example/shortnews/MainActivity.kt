package com.example.shortnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shortnews.databinding.ActivityMainBinding
import com.example.shortnews.models.Article
import com.example.shortnews.models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MyAdapter
    lateinit var newslist1:List<Article>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        newslist1=ArrayList()
        val retrofit= Retrofit.Builder().baseUrl("https://newsapi.org/").addConverterFactory(
            GsonConverterFactory.create()).build()
        val getApi=retrofit.create(NewsApi::class.java)
        getApi.getNews("bitcoin","5bc8f27a8cd74ccbbf7a5d678cb7b9cd").enqueue(object : Callback<News?> {
            override fun onResponse(call: Call<News?>, response: Response<News?>) {
                newslist1= response.body()?.articles !!
                adapter= MyAdapter(this@MainActivity,newslist1)
                binding.recyclerView.adapter=adapter
                binding.recyclerView.layoutManager= LinearLayoutManager(this@MainActivity
                )
            }

            override fun onFailure(call: Call<News?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "something went wrong", Toast.LENGTH_SHORT).show()
            }
        })


    }
}