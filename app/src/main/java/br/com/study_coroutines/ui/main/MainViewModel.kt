package br.com.study_coroutines.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.study_coroutines.domain.usecase.GetCharactersUseCase
import br.com.study_coroutines.network.Status
import kotlinx.coroutines.launch

class MainViewModel(private val charactersUseCase: GetCharactersUseCase) : ViewModel() {
    fun getCharacters() {
        viewModelScope.launch {
            val result = charactersUseCase.execute()
            when (result.status) {
                Status.SUCCESS -> {
                }
                Status.ERROR -> {
                }
            }
        }
    }
}
