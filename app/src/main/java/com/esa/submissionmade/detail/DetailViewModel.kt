package com.esa.submissionmade.detail

import androidx.lifecycle.ViewModel
import com.esa.submissionmade.core.domain.model.Movie
import com.esa.submissionmade.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}