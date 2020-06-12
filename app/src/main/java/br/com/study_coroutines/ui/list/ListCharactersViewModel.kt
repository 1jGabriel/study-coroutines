package br.com.study_coroutines.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.study_coroutines.data.PersonageDataSource
import br.com.study_coroutines.domain.repository.CharacterRepository
import br.com.study_coroutines.network.Status
import br.com.study_coroutines.ui.ViewAction
import kotlinx.coroutines.launch

class ListCharactersViewModel(private val repository: CharacterRepository) : ViewModel() {

    // val viewState = ViewState()
    val allPersonages = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            maxSize = 200
        )
    ){
        PersonageDataSource(repository)
    }.flow

    // fun dispatchAction(action: ViewAction) = when (action) {
    //     is ViewAction.Init -> getPosts()
    //     is ViewAction.Refresh -> getPosts()
    // }

    // private fun getCharacters() {
    //     viewModelScope.launch {
    //         val result = repository.getCharacters(1)
    //
    //         when (result.status) {
    //             Status.SUCCESS -> {
    //                 charactersLiveData.value?.addAll(result.data ?: emptyList())
    //             }
    //             Status.ERROR -> {
    //
    //             }
    //         }
    //     }
    // }

}
