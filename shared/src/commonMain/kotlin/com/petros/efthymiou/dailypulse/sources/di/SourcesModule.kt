package com.petros.efthymiou.dailypulse.sources.di

import com.petros.efthymiou.dailypulse.sources.application.SourcesUseCase
import com.petros.efthymiou.dailypulse.sources.data.SourcesDataSource
import com.petros.efthymiou.dailypulse.sources.data.SourcesRepository
import com.petros.efthymiou.dailypulse.sources.data.SourcesService
import com.petros.efthymiou.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {
    single { SourcesService(get()) }
    single { SourcesUseCase(get()) }
    single { SourcesViewModel(get()) }
    single { SourcesDataSource(get()) }
    single { SourcesRepository(get(), get()) }
}