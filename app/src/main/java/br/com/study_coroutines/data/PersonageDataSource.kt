package br.com.study_coroutines.data

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import br.com.study_coroutines.data.model.toCharacters
import br.com.study_coroutines.data.retrofit.RickNMortyApi
import br.com.study_coroutines.ui.model.CharacterUi
import retrofit2.HttpException
import java.io.IOException

class PersonageDataSource(
    private val api: RickNMortyApi
) : PagingSource<Int, CharacterUi>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterUi> {
        return try {
            val result = api.getCharacters(params.key ?: STARTING_PAGE_INDEX).toCharacters()
            Page(
                data = result,
                prevKey = params.key,
                nextKey = params.key?.plus(1) ?: STARTING_PAGE_INDEX.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
