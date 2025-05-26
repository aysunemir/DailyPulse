package com.petros.efthymiou.dailypulse.di

import com.petros.efthymiou.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module
import petros.efthymiou.dailypulse.db.DailyPulseDatabase

val databaseModule = module {
    single { DatabaseDriverFactory().createDriver() }
    single { DailyPulseDatabase(get()) }

}