package br.com.study_coroutines.data

import br.com.study_coroutines.data.model.toDomainModel
import br.com.study_coroutines.data.retrofit.RickNMortyApi
import br.com.study_coroutines.domain.repository.CharacterRepository
import br.com.study_coroutines.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val api: RickNMortyApi
) : CharacterRepository {
    override suspend fun getCharacters() = withContext(Dispatchers.IO) {
        try {
            val result = api.getCharacters()
            Resource.Success(result.toDomainModel())
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}