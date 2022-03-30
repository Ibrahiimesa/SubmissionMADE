package com.esa.submissionmade.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.esa.submissionmade.core.domain.model.Movie
import com.esa.submissionmade.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getFavoriteMovie(): LiveData<List<Movie>> =
        movieUseCase.getFavoriteMovie().asLiveData()
}