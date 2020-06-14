package br.com.study_coroutines.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.study_coroutines.data.model.toCharacters
import br.com.study_coroutines.data.retrofit.RickNMortyApi
import br.com.study_coroutines.domain.model.Character
import br.com.study_coroutines.domain.repository.CharacterRepository
import br.com.study_coroutines.network.Resource
import br.com.study_coroutines.ui.model.CharacterUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val api: RickNMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int) = withContext(Dispatchers.IO) {
        try {
            val result = api.getCharacters(page)
            Resource.Success(result.toCharacters())
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getCharactersById(id: String) = withContext(Dispatchers.IO) {
        try {
            val result = api.getCharacterById(id)
            Resource.Success(Character(result.id, result.image, result.name))
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override fun getCharactersPager(): Flow<PagingData<CharacterUi>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 500),
            pagingSourceFactory = { PersonageDataSource(api) }
        ).flow
    }
}
