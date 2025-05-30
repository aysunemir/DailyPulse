package com.petros.efthymiou.dailypulse.sources.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesService(private val client: HttpClient) {

    private val apiKey = "3b23d1dab5cd4b8696ec86ce5bc92774"

    suspend fun fetchSources(): List<SourceRaw> {
        val response: SourcesResponse =
            client.get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey").body()

        return response.sources
    }
}