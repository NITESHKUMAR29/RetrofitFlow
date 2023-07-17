package com.example.shortnews.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shortnews.models.News
import com.example.shortnews.repository.Repository
import kotlinx.coroutines.flow.Flow

class MyViewModel(private val repository: Repository): ViewModel() {
    val error=MutableLiveData<Throwable>()

//    fun getNews(q:String,from:String,apiKey:String):LiveData<News>{
//        val data=MutableLiveData<News>()
//        repository.getNews(q,from,apiKey,data,error)
//        return data
//    }

    fun getNews(q:String,from:String,apiKey:String):Flow<News>{


        return repository.getNews(q,from,apiKey)
    }

}