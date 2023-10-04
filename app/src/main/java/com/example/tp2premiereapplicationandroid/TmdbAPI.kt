package com.example.tp2premiereapplicationandroid

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getFilmAffiche(@Query("api_key") apikey: String): FilmPopulaire

}