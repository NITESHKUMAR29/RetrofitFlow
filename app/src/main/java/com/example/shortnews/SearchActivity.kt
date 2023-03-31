package com.example.shortnews

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shortnews.databinding.ActivityMainBinding
import com.example.shortnews.databinding.ActivitySearchBinding
import com.example.shortnews.models.Article
import com.example.shortnews.models.News
import com.google.android.material.internal.ViewUtils.showKeyboard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity() {
    lateinit var binding:ActivitySearchBinding
    lateinit var adapter: MyAdapter
    lateinit var newslist1:List<Article>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_search)
        binding.search.requestFocus()
        binding.search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
            }
            true
        }


    }


    @SuppressLint("SuspiciousIndentation")
    private fun performSearch() {
        newslist1=ArrayList()
        val retrofit= Retrofit.Builder().baseUrl("https://newsapi.org/").addConverterFactory(
            GsonConverterFactory.create()).build()
        val getApi=retrofit.create(NewsApi::class.java)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val previousDay = dateFormat.format(calendar.time)
        val searchType=binding.search.text.toString()
            getApi.getNews(searchType, previousDay,"5bc8f27a8cd74ccbbf7a5d678cb7b9cd").enqueue(object :
                Callback<News?> {
                override fun onResponse(call: Call<News?>, response: Response<News?>) {
                    newslist1= response.body()?.articles !!
                    adapter= MyAdapter(this@SearchActivity,this@SearchActivity,newslist1)
                    binding.recyclerView.adapter=adapter
                    binding.recyclerView.layoutManager= LinearLayoutManager(this@SearchActivity
                    )
                }

                override fun onFailure(call: Call<News?>, t: Throwable) {
                    Toast.makeText(this@SearchActivity, "something went wrong", Toast.LENGTH_SHORT).show()
                }
            })
    }

}