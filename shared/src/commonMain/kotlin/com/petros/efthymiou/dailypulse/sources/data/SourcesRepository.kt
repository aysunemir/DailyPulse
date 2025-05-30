package com.petros.efthymiou.dailypulse.sources.data

class SourcesRepository(
    private val dataSource: SourcesDataSource,
    private val service: SourcesService
) {

    suspend fun getAllSources(): List<SourceRaw> {
        val sourcesDb = dataSource.getAllSources()
        if (sourcesDb.isEmpty()) {
            val sources = service.fetchSources()
            dataSource.insertSources(sources)
            return sources
        }

        return sourcesDb
    }
}