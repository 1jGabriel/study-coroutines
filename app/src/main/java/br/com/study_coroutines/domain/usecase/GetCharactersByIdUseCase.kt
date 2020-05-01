package br.com.study_coroutines.domain.usecase

import br.com.study_coroutines.domain.repository.CharacterRepository

class GetCharactersByIdUseCase(private val characterRepository: CharacterRepository) {
    suspend fun execute(id: String) = characterRepository.getCharactersById(id)
}
