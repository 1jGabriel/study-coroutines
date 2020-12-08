package br.com.pagingcompose.di

import br.com.pagingcompose.data.CharacterRepositoryImpl
import br.com.pagingcompose.domain.repository.CharacterRepository
import br.com.pagingcompose.domain.usecase.GetCharactersUseCase
import br.com.pagingcompose.ui.detail.DetailViewModel
import br.com.pagingcompose.ui.list.ListCharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ListCharactersViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    factory<CharacterRepository> { CharacterRepositoryImpl(get()) }
    factory { GetCharactersUseCase(get()) }
}
