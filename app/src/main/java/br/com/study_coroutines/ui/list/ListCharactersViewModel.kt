package br.com.study_coroutines.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.study_coroutines.data.PersonageDataSource
import br.com.study_coroutines.domain.repository.CharacterRepository
import br.com.study_coroutines.network.Status
import br.com.study_coroutines.ui.ViewAction
import br.com.study_coroutines.ui.model.CharacterUi
import kotlinx.coroutines.launch

class ListCharactersViewModel(private val repository: CharacterRepository) : ViewModel() {

    // val viewState = ViewState()
    var charactersLiveData: LiveData<PagedList<CharacterUi>>

    fun dispatchAction(action: ViewAction) = when (action) {
        is ViewAction.Init -> getPosts()
        is ViewAction.Refresh -> getPosts()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            val result = repository.getCharacters(1)

            when (result.status) {
                Status.SUCCESS -> {
                    charactersLiveData.value?.addAll(result.data ?: emptyList())
                }
                Status.ERROR -> {

                }
            }
        }
    }

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        charactersLiveData = initializedPagedListBuilder(config).build()
    }

    fun getPosts(): LiveData<PagedList<CharacterUi>> = charactersLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<String, CharacterUi> {

        val dataSourceFactory = object : DataSource.Factory<Int, CharacterUi>() {
            override fun create(): DataSource<Int, CharacterUi> {
                return PersonageDataSource(viewModelScope, repository = repository)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}
