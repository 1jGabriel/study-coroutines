package br.com.study_coroutines.di

import br.com.study_coroutines.data.CharacterRepositoryImpl
import br.com.study_coroutines.domain.repository.CharacterRepository
import br.com.study_coroutines.domain.usecase.GetCharactersUseCase
import br.com.study_coroutines.network.ResponseHandler
import br.com.study_coroutines.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get()) }
    factory<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
    factory { GetCharactersUseCase(get()) }
    factory { ResponseHandler() }
}