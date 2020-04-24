package br.com.study_coroutines.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.study_coroutines.domain.usecase.GetCharactersByIdUseCase
import br.com.study_coroutines.network.Status
import kotlinx.coroutines.launch

class DetailViewModel(private val getCharactersByIdUseCase: GetCharactersByIdUseCase) : ViewModel() {
    fun dispatchAction(action: ViewAction) = when (action) {
        is ViewAction.Init -> getCharactersById(action.id)
    }

    private fun getCharactersById(id: String) {
        viewModelScope.launch {
            val result = getCharactersByIdUseCase.execute(id)

            when (result.status) {
                Status.SUCCESS -> {
                    println(result.data)
                }
                Status.ERROR -> {
                    println(result.exception)
                }
            }
        }
    }
}

sealed class ViewAction {
    data class Init(val id: String) : ViewAction()
}
