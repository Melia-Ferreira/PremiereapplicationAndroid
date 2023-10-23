package com.example.tp2premiereapplicationandroid

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface TmdbAPI {
    //requête films à l'affiche
    @GET("trending/movie/week")
    suspend fun getFilmAffiche(@Query("api_key") api_key: String, @Query("language") language : String): FilmPopulaire

    //requête recherche de films
    @GET("search/movie")
    suspend fun getFilmRecherche(@Query("query") query: String, @Query("api_key") api_key: String, @Query("language") language : String): FilmPopulaire

    //requête détails d'un film
    @GET("movie/{id}?append_to_response=credits")
    suspend fun getFilmDetail(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language : String): FilmDetail

    //requête séries à l'affiche
    @GET("trending/tv/week")
    suspend fun getSerieAffiche(@Query("api_key") api_key: String, @Query("language") language : String): SeriePopulaire

    //requête recherche de series
    @GET("search/tv")
    suspend fun getSerieRecherche(@Query("query") query: String, @Query("api_key") api_key: String, @Query("language") language : String): SeriePopulaire

    //requête détails d'une série
    @GET("tv/{id}?append_to_response=credits")
    suspend fun getSerieDetail(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language : String): SerieDetail

    //requête acteurs populaires
    @GET("trending/person/week")
    suspend fun getActeurPopulaire(@Query("api_key") api_key: String, @Query("language") language : String): ActeurPopulaire

    //requête recherche d'acteurs
    @GET("search/person")
    suspend fun getActeurRecherche(@Query("query") query: String, @Query("api_key") api_key: String, @Query("language") language : String): ActeurPopulaire

    //requête détails d'un acteur
    @GET("person/{id}?append_to_response=credits")
    suspend fun getActeurDetail(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language : String): ActeurDetail

    //requête filmographie acteur
    @GET("person/{id}/movie_credits?append_to_response=credits")
    suspend fun getFilmographie(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language : String): Filmographie


}
