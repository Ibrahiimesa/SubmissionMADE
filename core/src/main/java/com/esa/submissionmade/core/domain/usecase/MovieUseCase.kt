package com.esa.submissionmade.core.domain.usecase

import com.esa.submissionmade.core.data.Resource
import com.esa.submissionmade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(sort: String): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}