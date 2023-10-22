package com.example.tp2premiereapplicationandroid

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getFilmAffiche(@Query("api_key") api_key: String): FilmPopulaire

    @GET("search/movie")
    suspend fun getFilmRecherche(@Query("query") query: String, @Query("api_key") api_key: String): FilmPopulaire

    @GET("movie/{id}?append_to_response=credits")
    suspend fun getFilmDetail(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language : String): FilmDetail
    @GET("trending/tv/week")
    suspend fun getSeriesAffiche(@Query("api_key") api_key: String): FilmPopulaire

    @GET("trending/tv/week")
    suspend fun getSerieAffiche(@Query("api_key") api_key: String): SeriePopulaire
}
