package br.com.pagingcompose.domain.usecase

import br.com.pagingcompose.domain.repository.CharacterRepository

class GetCharactersUseCase(private val characterRepository: CharacterRepository) {
    suspend fun execute() = characterRepository.getCharacters()
}
