package br.com.study_coroutines.ui.main

import br.com.study_coroutines.domain.model.Character
import br.com.study_coroutines.ui.core.SingleLiveEvent

class ViewState(
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent<Boolean>().apply { value = true },
    val isError: SingleLiveEvent<Boolean> = SingleLiveEvent<Boolean>().apply { value = false },
    val characters: SingleLiveEvent<List<Character>> =
        SingleLiveEvent<List<Character>>().apply { value = listOf() }
)
