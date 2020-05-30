package br.com.study_coroutines.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import br.com.study_coroutines.domain.repository.CharacterRepository
import br.com.study_coroutines.network.Resource
import br.com.study_coroutines.ui.model.CharacterUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PersonageDataSource(private val scope: CoroutineScope, private val repository: CharacterRepository) :
    PageKeyedDataSource<Int, CharacterUi>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, CharacterUi>) {
        scope.launch {
            when (val response = repository.getCharacters(page = 1)) {
                is Resource.Success -> {
                    callback.onResult(response.content?.toMutableList() ?: listOf(), null, 2)
                }
                else -> {
                    Log.e("PostsDataSource", "Failed to fetch data!")
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterUi>) {
        scope.launch {
            when (val response = repository.getCharacters(page = params.requestedLoadSize)) {
                is Resource.Success -> {
                    callback.onResult(response.content ?: listOf(), params.key + 1)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterUi>) {
        scope.launch {
            when (val response = repository.getCharacters(page = params.requestedLoadSize)) {
                is Resource.Success -> {
                    callback.onResult(response.content ?: listOf(), params.key + 1)
                }
            }
        }
    }
}
