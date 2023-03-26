package com.example.shortnews.models

import com.example.shortnews.models.Article

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)