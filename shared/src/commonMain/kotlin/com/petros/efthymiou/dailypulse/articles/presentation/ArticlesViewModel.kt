package com.petros.efthymiou.dailypulse.articles.presentation

import com.petros.efthymiou.dailypulse.BaseViewModel
import com.petros.efthymiou.dailypulse.articles.application.ArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val articlesUseCase: ArticlesUseCase
) : BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticleState> =
        MutableStateFlow(ArticleState(loading = true))
    val articleState: StateFlow<ArticleState> get() = _articleState

    init {
        getArticles()
    }

    fun getArticles(forceRefresh: Boolean = false) {
        scope.launch {
            _articleState.emit(
                ArticleState(
                    loading = true,
                    articles = _articleState.value.articles
                )
            )

            val fetchedArticles = articlesUseCase.getArticles(forceRefresh)

            _articleState.emit(ArticleState(articles = fetchedArticles))
        }
    }

}