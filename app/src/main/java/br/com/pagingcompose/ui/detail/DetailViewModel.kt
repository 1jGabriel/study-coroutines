package br.com.pagingcompose.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pagingcompose.domain.repository.CharacterRepository
import br.com.pagingcompose.ui.model.Personage
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: CharacterRepository) : ViewModel() {

    val characterDetail: MutableLiveData<Personage> = MutableLiveData()
    fun dispatchAction(action: ViewAction) = when (action) {
        is ViewAction.Init -> getCharactersById(action.id)
    }

    private fun getCharactersById(id: String) {
        viewModelScope.launch {
            val result = repository.getCharacterById(id)
            characterDetail.value = result.data
        }
    }
}

sealed class ViewAction {
    data class Init(val id: String) : ViewAction()
}
