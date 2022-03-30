package com.esa.submissionmade.core.utils

import com.esa.submissionmade.core.data.source.local.entity.MovieEntity
import com.esa.submissionmade.core.data.source.remote.response.MovieResponse
import com.esa.submissionmade.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.overview,
                it.originalLanguage,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.id,
                it.backdropPath,
                it.title,
                it.voteCount,
                it.posterPath,
                isFavorite = false,
                isTvShow = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.overview,
                it.originalLanguage,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.id,
                it.backdropPath,
                it.title,
                it.voteCount,
                it.posterPath,
                isFavorite = it.isFavorite,
                isTvShows = it.isTvShow
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        input.overview,
        input.originalLanguage,
        input.releaseDate,
        input.popularity,
        input.voteAverage,
        input.id,
        input.backdropPath,
        input.title,
        input.voteCount,
        input.posterPath,
        isFavorite = input.isFavorite,
        isTvShow = input.isTvShows
    )
}