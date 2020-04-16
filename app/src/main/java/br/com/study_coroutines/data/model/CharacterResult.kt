package br.com.study_coroutines.data.model

import br.com.study_coroutines.domain.model.CharacterDomainModel
import com.squareup.moshi.Json

data class CharacterResult(
    @field:Json(name = "results")
    val results: List<Result>? = listOf()
) {
    data class Result(
        @field:Json(name = "created")
        val created: String = "",
        @field:Json(name = "episode")
        val episode: List<String> = listOf(),
        @field:Json(name = "gender")
        val gender: String = "",
        @field:Json(name = "id")
        val id: Int = 0,
        @field:Json(name = "image")
        val image: String = "",
        @field:Json(name = "location")
        val location: Location = Location(),
        @field:Json(name = "name")
        val name: String = "",
        @field:Json(name = "origin")
        val origin: Origin = Origin(),
        @field:Json(name = "species")
        val species: String = "",
        @field:Json(name = "status")
        val status: String = "",
        @field:Json(name = "type")
        val type: String = "",
        @field:Json(name = "url")
        val url: String = ""
    ) {
        data class Location(
            @field:Json(name = "name")
            val name: String = "",
            @field:Json(name = "url")
            val url: String = ""
        )

        data class Origin(
            @field:Json(name = "name")
            val name: String = "",
            @field:Json(name = "url")
            val url: String = ""
        )
    }
}

fun CharacterResult.toDomainModel(): List<CharacterDomainModel> {
    return this.results?.map {
        CharacterDomainModel(it.id, it.image, it.name)
    } ?: listOf()
}
