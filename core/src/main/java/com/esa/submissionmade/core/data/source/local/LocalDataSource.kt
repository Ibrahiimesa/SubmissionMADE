package com.esa.submissionmade.core.data.source.local

import com.esa.submissionmade.core.data.source.local.entity.MovieEntity
import com.esa.submissionmade.core.data.source.local.room.MovieDao
import com.esa.submissionmade.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryMovies(sort)
        return movieDao.getAllMovie(query)
    }

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}