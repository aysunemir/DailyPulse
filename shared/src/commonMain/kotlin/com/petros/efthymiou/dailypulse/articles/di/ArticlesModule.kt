package com.petros.efthymiou.dailypulse.articles.di

import com.petros.efthymiou.dailypulse.articles.data.ArticlesDataSource
import com.petros.efthymiou.dailypulse.articles.data.ArticlesRepository
import com.petros.efthymiou.dailypulse.articles.data.ArticlesService
import com.petros.efthymiou.dailypulse.articles.application.ArticlesUseCase
import com.petros.efthymiou.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single { ArticlesService(get()) }
    single { ArticlesUseCase(get()) }
    single { ArticlesViewModel(get()) }
    single { ArticlesDataSource(get()) }
    single { ArticlesRepository(get(), get()) }
}