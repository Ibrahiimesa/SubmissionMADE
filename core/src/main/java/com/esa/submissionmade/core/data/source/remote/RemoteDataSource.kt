package com.esa.submissionmade.core.data.source.remote

import android.content.ContentValues
import android.util.Log
import com.esa.submissionmade.core.data.source.remote.network.ApiResponse
import com.esa.submissionmade.core.data.source.remote.network.ApiService
import com.esa.submissionmade.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovie()
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(ContentValues.TAG, "getMovies: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}

