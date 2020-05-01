package br.com.study_coroutines.domain.usecase

import br.com.study_coroutines.domain.repository.CharacterRepository

class GetCharactersUseCase(private val characterRepository: CharacterRepository) {
    suspend fun execute() = characterRepository.getCharacters()
}
