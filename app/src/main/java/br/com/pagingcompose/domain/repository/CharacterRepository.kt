package br.com.pagingcompose.domain.repository

import androidx.paging.PagingData
import br.com.pagingcompose.network.Resource
import br.com.pagingcompose.ui.model.CharacterUi
import br.com.pagingcompose.ui.model.Personage
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(page: Int = 1): Resource<List<CharacterUi>>
    fun getCharactersPager(): Flow<PagingData<CharacterUi>>
    suspend fun getCharacterById(id: String): Resource<Personage>
}
