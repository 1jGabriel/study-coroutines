package br.com.study_coroutines.data

import br.com.study_coroutines.data.model.toDomainModel
import br.com.study_coroutines.data.retrofit.RickNMortyApi
import br.com.study_coroutines.domain.model.CharacterDomainModel
import br.com.study_coroutines.domain.repository.CharacterRepository
import br.com.study_coroutines.network.Resource
import br.com.study_coroutines.network.ResponseHandler

class CharacterRepositoryImpl(
    private val api: RickNMortyApi,
    private val responseHandler: ResponseHandler
) : CharacterRepository {
    override suspend fun getCharacters(): Resource<List<CharacterDomainModel>> {
        return try {
            val response = api.getCharacters()
            responseHandler.handleSuccess(response.toDomainModel())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}