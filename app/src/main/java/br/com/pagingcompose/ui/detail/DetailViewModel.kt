// package br.com.study_coroutines.ui.detail
//
// import androidx.lifecycle.ViewModel
// import androidx.lifecycle.viewModelScope
// import br.com.study_coroutines.network.Status
// import kotlinx.coroutines.launch
//
// class DetailViewModel(private val getCharactersByIdUseCase: GetCharactersByIdUseCase) : ViewModel() {
//
//     val viewState = DetailViewState()
//
//     fun dispatchAction(action: ViewAction) = when (action) {
//         is ViewAction.Init -> getCharactersById(action.id)
//     }
//
//     private fun getCharactersById(id: String) {
//         viewModelScope.launch {
//             val result = getCharactersByIdUseCase.execute(id)
//
//             when (result.status) {
//                 Status.SUCCESS -> {
//                     viewState.isLoading.postValue(false)
//                     viewState.isError.postValue(false)
//                     viewState.character.postValue(result.data)
//                 }
//                 Status.ERROR -> {
//                     viewState.isLoading.postValue(false)
//                     viewState.isError.postValue(true)
//                 }
//             }
//         }
//     }
// }
//
// sealed class ViewAction {
//     data class Init(val id: String) : ViewAction()
// }
//
// class DetailViewState(
//     val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent<Boolean>().apply { value = true },
//     val isError: SingleLiveEvent<Boolean> = SingleLiveEvent<Boolean>().apply { value = false },
//     val character: SingleLiveEvent<Character> =
//         SingleLiveEvent<Character>().apply { value = Character(0, "", "") }
// )
