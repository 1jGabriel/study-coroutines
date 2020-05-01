package br.com.study_coroutines.di

import br.com.study_coroutines.data.CharacterRepositoryImpl
import br.com.study_coroutines.domain.repository.CharacterRepository
import br.com.study_coroutines.domain.usecase.GetCharactersUseCase
import br.com.study_coroutines.ui.detail.DetailViewModel
import br.com.study_coroutines.ui.list.ListCharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ListCharactersViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    factory<CharacterRepository> { CharacterRepositoryImpl(get()) }
    factory { GetCharactersUseCase(get()) }
}
