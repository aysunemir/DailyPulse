package com.petros.efthymiou.dailypulse.sources.data

import petros.efthymiou.dailypulse.db.DailyPulseDatabase

class SourcesDataSource(private val database: DailyPulseDatabase) {

    fun getAllSources(): List<SourceRaw> =
        database.dailyPulseDatabaseQueries.selectAllSources(::mapToSourceRaw).executeAsList()

    fun insertSources(sources: List<SourceRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            sources.forEach { source ->
                insertSource(source)
            }
        }
    }

    fun clearSources() {
        database.dailyPulseDatabaseQueries.removeAllSources()
    }

    private fun insertSource(source: SourceRaw) {
        database.dailyPulseDatabaseQueries.insertSource(
            id = source.id,
            name = source.name,
            desc = source.description,
            language = source.language,
            country = source.country
        )
    }

    private fun mapToSourceRaw(
        id: String,
        name: String,
        desc: String,
        language: String,
        country: String
    ):SourceRaw = SourceRaw(
        id = id,
        name = name,
        description = desc,
        language = language,
        country = country
    )
}