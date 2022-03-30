package com.esa.submissionmade.di

import com.esa.submissionmade.core.domain.usecase.MovieInteractor
import com.esa.submissionmade.core.domain.usecase.MovieUseCase
import com.esa.submissionmade.detail.DetailViewModel
import com.esa.submissionmade.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}