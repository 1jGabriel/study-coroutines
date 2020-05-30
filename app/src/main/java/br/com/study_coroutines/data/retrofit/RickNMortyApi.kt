package br.com.study_coroutines.data.retrofit

import br.com.study_coroutines.data.model.CharacterResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickNMortyApi {

    @GET("api/character/")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResult

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): CharacterResult.Character
}
