package br.com.study_coroutines.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.study_coroutines.domain.usecase.GetCharactersUseCase
import br.com.study_coroutines.network.Status
import br.com.study_coroutines.ui.ViewAction
import br.com.study_coroutines.ui.main.ViewState
import kotlinx.coroutines.launch

class ListCharactersViewModel(private val charactersUseCase: GetCharactersUseCase) : ViewModel() {

    val viewState = ViewState()

    fun dispatchAction(action: ViewAction) = when (action) {
        is ViewAction.Init -> getCharacters()
        is ViewAction.Refresh -> getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            val result = charactersUseCase.execute()

            when (result.status) {
                Status.SUCCESS -> {
                    viewState.isLoading.postValue(false)
                    viewState.isError.postValue(false)
                    viewState.characters.postValue(result.data)
                }
                Status.ERROR -> {
                    viewState.isLoading.postValue(false)
                    viewState.isError.postValue(true)
                }
            }
        }
    }
}
