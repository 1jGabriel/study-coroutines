package br.com.study_coroutines.domain.repository

import br.com.study_coroutines.domain.model.CharacterDomainModel
import br.com.study_coroutines.network.Resource

interface CharacterRepository {
    suspend fun getCharacters(): Resource<List<CharacterDomainModel>>
}