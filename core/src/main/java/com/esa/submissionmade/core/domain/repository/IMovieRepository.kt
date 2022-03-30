package com.esa.submissionmade.core.domain.repository

import com.esa.submissionmade.core.data.Resource
import com.esa.submissionmade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(sort : String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(tourism: Movie, state: Boolean)

}