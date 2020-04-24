package br.com.study_coroutines.domain.repository

import br.com.study_coroutines.domain.model.Character
import br.com.study_coroutines.network.Resource

interface CharacterRepository {
    suspend fun getCharacters(): Resource<List<Character>>
    suspend fun getCharactersById(id: String): Resource<Character>
}