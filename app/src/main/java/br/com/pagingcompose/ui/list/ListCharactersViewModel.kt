package br.com.pagingcompose.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.pagingcompose.domain.repository.CharacterRepository
import br.com.pagingcompose.ui.model.CharacterUi
import kotlinx.coroutines.flow.Flow

class ListCharactersViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    fun getCharacters(): Flow<PagingData<CharacterUi>> {
        return repository.getCharactersPager().cachedIn(viewModelScope)
    }
}
