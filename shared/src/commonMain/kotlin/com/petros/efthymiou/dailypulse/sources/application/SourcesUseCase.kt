package com.petros.efthymiou.dailypulse.sources.application

import com.petros.efthymiou.dailypulse.sources.data.SourceRaw
import com.petros.efthymiou.dailypulse.sources.data.SourcesRepository

class SourcesUseCase(val repo: SourcesRepository) {

    suspend fun getAllSources(): List<Source> {
        val sourcesRaw = repo.getAllSources()
        return mapSources(sourcesRaw)
    }

    private fun mapSources(sourcesRaw: List<SourceRaw>): List<Source> =
        sourcesRaw.map {
            Source(
                id = it.id,
                title = it.name,
                desc = it.description,
                languageCounty ="${it.language} - ${it.country}",
            )
        }
}