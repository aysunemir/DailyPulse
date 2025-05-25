package com.petros.efthymiou.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val client: HttpClient) {

    private val country = "us"
    private val category = "business"
    private val apiKey = "3b23d1dab5cd4b8696ec86ce5bc92774"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse =
            client.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
                .body()

        return response.articles
    }
}