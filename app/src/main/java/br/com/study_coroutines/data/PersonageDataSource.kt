package br.com.study_coroutines.data

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import br.com.study_coroutines.domain.repository.CharacterRepository
import br.com.study_coroutines.ui.model.CharacterUi
import retrofit2.HttpException
import java.io.IOException

class PersonageDataSource(
    private val repository: CharacterRepository
) : PagingSource<Int, CharacterUi>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterUi> {
        return try {
            val data = repository.getCharacters(params.key ?: 1).data

            Page(
                data = data ?: emptyList(),
                prevKey = params.key,
                nextKey = params.key?.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
