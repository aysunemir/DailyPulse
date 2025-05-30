package com.petros.efthymiou.dailypulse.sources.presentation

import com.petros.efthymiou.dailypulse.BaseViewModel
import com.petros.efthymiou.dailypulse.sources.application.SourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourcesViewModel(
    private val sourcesUseCase: SourcesUseCase
) : BaseViewModel() {

    private val _sourceState: MutableStateFlow<SourceState> =
        MutableStateFlow(SourceState(loading = true))
    val sourceState: StateFlow<SourceState> get() = _sourceState

    init {
        getSources()
    }

    fun getSources() {
        scope.launch {
            _sourceState.emit(
                SourceState(
                    loading = true,
                    sources = _sourceState.value.sources
                )
            )

            val fetchedSources = sourcesUseCase.getAllSources()

            _sourceState.emit(SourceState(sources = fetchedSources))
        }

    }
}