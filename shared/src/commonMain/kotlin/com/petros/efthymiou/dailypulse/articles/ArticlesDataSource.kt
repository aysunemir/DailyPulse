package com.petros.efthymiou.dailypulse.articles

import petros.efthymiou.dailypulse.db.DailyPulseDatabase


class ArticlesDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticles(): List<ArticleRaw> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { article ->
                insertArticle(article)
            }
        }
    }

    fun clearArticles() {
        database.dailyPulseDatabaseQueries.removeAllArticles()
    }

    private fun insertArticle(article: ArticleRaw) {
        database.dailyPulseDatabaseQueries.insertArticle(
            title = article.title,
            desc = article.desc,
            date = article.date,
            imageUrl = article.imageUrl
        )
    }

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        imageUrl: String?
    ): ArticleRaw = ArticleRaw(
        title = title,
        desc = desc,
        date = date,
        imageUrl = imageUrl
    )
}