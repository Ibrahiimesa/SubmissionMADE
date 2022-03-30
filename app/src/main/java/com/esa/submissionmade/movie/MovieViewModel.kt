package com.esa.submissionmade.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.esa.submissionmade.core.data.Resource
import com.esa.submissionmade.core.domain.model.Movie
import com.esa.submissionmade.core.domain.usecase.MovieUseCase

class MovieViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMovie(sort: String): LiveData<Resource<List<Movie>>> =
        movieUseCase.getAllMovie(sort).asLiveData()
}