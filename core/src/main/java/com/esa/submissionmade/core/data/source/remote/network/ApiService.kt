package com.esa.submissionmade.core.data.source.remote.network

import com.esa.submissionmade.core.BuildConfig.API_KEY
import com.esa.submissionmade.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = API_KEY
    ): ListMovieResponse
}
