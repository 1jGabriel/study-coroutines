package br.com.study_coroutines.data.retrofit

import br.com.study_coroutines.data.model.CharacterResult
import retrofit2.http.GET

interface RickNMortyApi {

    @GET("api/character")
    suspend fun getCharacters(): CharacterResult
}