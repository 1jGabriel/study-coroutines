package br.com.study_coroutines.domain.repository

import br.com.study_coroutines.domain.model.Character
import br.com.study_coroutines.network.Resource
import br.com.study_coroutines.ui.model.CharacterUi

interface CharacterRepository {
    suspend fun getCharacters(page: Int = 1): Resource<List<CharacterUi>>
    suspend fun getCharactersById(id: String): Resource<Character>
}
