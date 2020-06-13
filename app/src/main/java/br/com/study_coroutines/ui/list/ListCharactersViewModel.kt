package br.com.study_coroutines.ui.list

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.study_coroutines.data.PersonageDataSource
import br.com.study_coroutines.domain.repository.CharacterRepository

class ListCharactersViewModel(private val repository: CharacterRepository) : ViewModel() {

    val allPersonages = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            maxSize = 100
        )
    ) {
        PersonageDataSource(repository)
    }.flow
}
