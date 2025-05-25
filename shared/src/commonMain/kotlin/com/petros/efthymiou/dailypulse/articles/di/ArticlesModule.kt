package com.petros.efthymiou.dailypulse.articles.di

import com.petros.efthymiou.dailypulse.articles.ArticlesService
import com.petros.efthymiou.dailypulse.articles.ArticlesUseCase
import com.petros.efthymiou.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single { ArticlesService(get()) }
    single { ArticlesUseCase(get()) }
    single { ArticlesViewModel(get()) }

}