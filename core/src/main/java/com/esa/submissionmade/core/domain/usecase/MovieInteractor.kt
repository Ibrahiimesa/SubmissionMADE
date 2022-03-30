package com.esa.submissionmade.core.domain.usecase

import com.esa.submissionmade.core.domain.model.Movie
import com.esa.submissionmade.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovie(sort: String) = movieRepository.getAllMovie(sort)

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}