package com.example.tp2premiereapplicationandroid



import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//classe pour stocker et gérer des données de manière cohérente et réactive
class MainViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    //MutableStateFlow est utilisée pour gérer les flux de données en temps réel. Elle permet de stocker une valeur mutable qui peut
    // changer au fil du temps, et elle expose cette valeur sous forme d'un flux
    val movies = MutableStateFlow<List<Movie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val film = MutableStateFlow<FilmDetail>(FilmDetail())
    val serie = MutableStateFlow<SerieDetail>(SerieDetail())
    val acteurs = MutableStateFlow<List<Acteur>>(listOf())
    val acteur = MutableStateFlow<ActeurDetail>(ActeurDetail())
   // val filmo = MutableStateFlow<Filmographie>(listOf())


    //clé de l'API TMTB
    val apikey = "d99da6596d7cad8888832ecc40a57d4b"


    //configuration de Retrofit afin de faire des requêtes API
    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    //récupérer la liste des films à l'affiche
    fun getFilmsInitiaux() {
        viewModelScope.launch {
            movies.value = service.getFilmAffiche(apikey,"fr").results //résult quand il s'agit d'une liste
        }
    }

    //récupérer une liste de films à partir d'un mot clé
    fun getFilmsRecherche(query: String) {
        viewModelScope.launch {
            movies.value = service.getFilmRecherche(query, apikey,"fr").results
        }
    }

    //récupérer les détails d'un film à partir de son ID
    fun getDetailFilm(movieid: String) {
        viewModelScope.launch {
            film.value = service.getFilmDetail(movieid, apikey, "fr")
        }
    }

    //récupérer la liste des séries à l'affiche
    fun getSeriesInitiales() {
        viewModelScope.launch {
            series.value = service.getSerieAffiche(apikey, "fr").results
        }
    }

    //récupérer une liste de séries à partir d'un mot clé
    fun getSeriesRecherche(query: String) {
        viewModelScope.launch {
            series.value = service.getSerieRecherche(query, apikey, "fr").results
        }
    }

    //récupérer les détails d'une série à partir de son ID
    fun getDetailSerie(serieid: String) {
        viewModelScope.launch {
            serie.value = service.getSerieDetail(serieid, apikey, "fr")
        }
    }

    //récupérer la liste des acteurs populaires
    fun getActeursPopulaires() {
        viewModelScope.launch {
            acteurs.value = service.getActeurPopulaire(apikey, "fr").results
        }
    }

    //récupérer une liste d'acteurs à partir d'un mot clé
    fun getActeursRecherche(query: String) {
        viewModelScope.launch {
            acteurs.value = service.getActeurRecherche(query, apikey, "fr").results
        }
    }

    //récupérer les détails d'un acteur à partir de son ID
    fun getDetailActeur(acteurid: String) {
        viewModelScope.launch {
            acteur.value = service.getActeurDetail(acteurid, apikey, "fr")
        }
    }

    //récupérer lla filmographie d'un acteur à partir de son ID
    /*fun getFilmographieActeur(acteurid: String) {
        viewModelScope.launch {
            filmo.value = service.getFilmographie(acteurid, apikey, "fr")
        }
    }*/
}