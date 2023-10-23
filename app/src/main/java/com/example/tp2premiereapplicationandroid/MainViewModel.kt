package com.example.tp2premiereapplicationandroid



import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val film = MutableStateFlow<FilmDetail>(FilmDetail())
    val serie = MutableStateFlow<SerieDetail>(SerieDetail())
    val acteurs = MutableStateFlow<List<Acteur>>(listOf())
    val acteur = MutableStateFlow<ActeurDetail>(ActeurDetail())
   // val filmo = MutableStateFlow<Filmographie>(listOf())

   // private val filmID: String? = savedStateHandle["filmID"]

    val apikey = "d99da6596d7cad8888832ecc40a57d4b"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun getFilmsInitiaux() {
        viewModelScope.launch {
            movies.value = service.getFilmAffiche(apikey,"fr").results

            //   Log.v("xxx","taille:" + movies.value.size)
        }
    }

    fun getFilmsRecherche(query: String) {
        viewModelScope.launch {
            movies.value = service.getFilmRecherche(query, apikey,"fr").results
            Log.v("xxx", "taille:" + movies.value.size)
            Log.v("xxx", "taille:" + movies.value[0].title)
        }
    }

    fun getDetailFilm(movieid: String) {
        viewModelScope.launch {
            film.value = service.getFilmDetail(movieid/*filmID?:""*/, apikey, "fr")
            //Log.v("xxx", "titre:" + films.original_title)
        }
    }

    fun getSeriesInitiales() {
        viewModelScope.launch {
            series.value = service.getSerieAffiche(apikey, "fr").results

            //   Log.v("xxx","taille:" + movies.value.size)
        }
    }
    fun getSeriesRecherche(query: String) {
        viewModelScope.launch {
            series.value = service.getSerieRecherche(query, apikey, "fr").results
        }
    }

    fun getDetailSerie(serieid: String) {
        viewModelScope.launch {
            serie.value = service.getSerieDetail(serieid, apikey, "fr")
        }
    }

    fun getActeursPopulaires() {
        viewModelScope.launch {
            acteurs.value = service.getActeurPopulaire(apikey, "fr").results

            //   Log.v("xxx","taille:" + movies.value.size)
        }
    }

    fun getActeursRecherche(query: String) {
        viewModelScope.launch {
            acteurs.value = service.getActeurRecherche(query, apikey, "fr").results
        }
    }

    fun getDetailActeur(acteurid: String) {
        viewModelScope.launch {
            acteur.value = service.getActeurDetail(acteurid, apikey, "fr")
        }
    }

    /*fun getFilmographieActeur(acteurid: String) {
        viewModelScope.launch {
            filmo.value = service.getFilmographie(acteurid, apikey, "fr")
        }
    }*/
}