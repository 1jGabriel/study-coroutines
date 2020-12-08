package br.com.pagingcompose.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.pagingcompose.data.model.toCharacters
import br.com.pagingcompose.data.retrofit.RickNMortyApi
import br.com.pagingcompose.domain.repository.CharacterRepository
import br.com.pagingcompose.network.Resource
import br.com.pagingcompose.ui.model.CharacterUi
import br.com.pagingcompose.ui.model.Personage
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

    override fun getCharactersPager(): Flow<PagingData<CharacterUi>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 500),
            pagingSourceFactory = { PersonageDataSource(api) }
        ).flow
    }

    override suspend fun getCharacterById(id: String) = withContext(Dispatchers.IO) {
        try {
            val result = api.getCharacterById(id)
            Resource.Success(Personage(id = result.id, image = result.image, name = result.name))
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}
