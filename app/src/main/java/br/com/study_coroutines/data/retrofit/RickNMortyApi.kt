package br.com.study_coroutines.data.retrofit

import br.com.study_coroutines.data.model.CharacterResult
import retrofit2.http.GET
import retrofit2.http.Path

interface RickNMortyApi {

    @GET("api/character")
    suspend fun getCharacters(): CharacterResult

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): CharacterResult.Character
}
