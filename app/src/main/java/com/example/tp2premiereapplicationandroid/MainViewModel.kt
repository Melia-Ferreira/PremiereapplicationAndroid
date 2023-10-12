package com.example.tp2premiereapplicationandroid



import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(listOf())

    val apikey = "d99da6596d7cad8888832ecc40a57d4b"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun getFilmsInitiaux(){
        viewModelScope.launch {
            val films = service.getFilmAffiche(apikey)
            movies.value = films.results

         //   Log.v("xxx","taille:" + movies.value.size)
            }
        }

    fun getFilmsRecherche(query: String){
        viewModelScope.launch {
                movies.value = service.getFilmRecherche(query, apikey).results
            Log.v("xxx","taille:" + movies.value.size)
            Log.v("xxx","taille:" + movies.value[0].title)
            }
    }
    }