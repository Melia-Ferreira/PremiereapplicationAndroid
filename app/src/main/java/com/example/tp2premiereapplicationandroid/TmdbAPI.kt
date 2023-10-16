package com.example.tp2premiereapplicationandroid

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getFilmAffiche(@Query("api_key") api_key: String): FilmPopulaire

    @GET("search/movie")
    suspend fun getFilmRecherche(@Query("query") query: String, @Query("api_key") api_key: String): FilmPopulaire

    @GET("movie/{movie_id}")
    suspend fun getFilmDetail(@Query("movie_id") movie_id: String, @Query("api_key") api_key: String): FilmDetail
}
