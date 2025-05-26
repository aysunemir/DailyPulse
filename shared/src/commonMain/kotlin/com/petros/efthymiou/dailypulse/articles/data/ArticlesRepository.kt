package com.petros.efthymiou.dailypulse.articles.data

import com.petros.efthymiou.dailypulse.articles.data.ArticlesService

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {

    suspend fun getArticles(forceRefresh: Boolean): List<ArticleRaw> {
        if (forceRefresh) {
            dataSource.clearArticles()
           return fetchArticles()
        }
        val articlesDb = dataSource.getAllArticles()
        if (articlesDb.isEmpty()) {
            return fetchArticles()
        }

        return articlesDb
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchedArticles = service.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}