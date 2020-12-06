package br.com.pagingcompose.ui.main

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ListCharactersViewModelTest {

    // @get:Rule
    // var instantExecutorRule = InstantTaskExecutorRule()
    //
    // private lateinit var listCharactersViewModel: ListCharactersViewModel
    //
    // private var useCase = mockk<GetCharactersUseCase>()
    // private var successResponse = Resource.Success<List<Character>>(listOf())
    // private var errorResponse = mockk<Exception>()
    //
    // @Before
    // fun setup() {
    //     Dispatchers.setMain(Dispatchers.Unconfined)
    //     listCharactersViewModel = ListCharactersViewModel(useCase)
    // }
    //
    // @Test
    // fun `should show error true when call Init dispatch action `() {
    //     coEvery { useCase.execute() } returns Resource.Error(errorResponse)
    //     listCharactersViewModel.dispatchAction(ViewAction.Init)
    //     coVerify { useCase.execute() }
    //
    //     Assert.assertEquals(listCharactersViewModel.viewState.isError.value, true)
    // }
    //
    // @Test
    // fun `should success be true when call Init dispatch action `() {
    //     runBlockingTest {
    //         useCase.execute()
    //     }
    //     coEvery { useCase.execute() } returns successResponse
    //     listCharactersViewModel.dispatchAction(ViewAction.Init)
    //     coVerify { useCase.execute() }
    //     Assert.assertEquals(successResponse.data, listCharactersViewModel.viewState.characters.value)
    // }
}
