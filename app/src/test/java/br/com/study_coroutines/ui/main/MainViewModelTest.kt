package br.com.study_coroutines.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.study_coroutines.domain.model.Character
import br.com.study_coroutines.domain.usecase.GetCharactersUseCase
import br.com.study_coroutines.network.Resource
import br.com.study_coroutines.ui.ViewAction
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mainViewModel: MainViewModel

    private var useCase = mockk<GetCharactersUseCase>()
    private var successResponse = Resource.Success<List<Character>>(listOf())
    private var errorResponse = mockk<Exception>()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined )
        mainViewModel = MainViewModel(useCase)
    }

    @Test
    fun `should show error true when call Init dispatch action `() {
        coEvery { useCase.execute() } returns Resource.Error(errorResponse)
        mainViewModel.dispatchAction(ViewAction.Init)
        coVerify { useCase.execute() }

        Assert.assertEquals(mainViewModel.viewState.isError.value, true)
    }

    @Test
    fun `should success be true when call Init dispatch action `() {
        runBlockingTest {
            useCase.execute()
        }
        coEvery { useCase.execute() } returns successResponse
        mainViewModel.dispatchAction(ViewAction.Init)
        coVerify { useCase.execute() }
        Assert.assertEquals(successResponse.data, mainViewModel.viewState.characters.value)
    }
}