package com.example.tp2premiereapplicationandroid

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import coil.compose.rememberImagePainter
import kotlin.io.path.createTempDirectory
import java.text.SimpleDateFormat
import java.util.Date
import java.text.DateFormatSymbols
import java.text.ParseException
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsFilm(navController: NavController,
         windowClass: WindowSizeClass,
         viewModel: MainViewModel,
                movieid: String
) {
    val films by viewModel.film.collectAsState()
    LaunchedEffect(true) {
        viewModel.getDetailFilm(movieid)
    }
    /*Text(
        text = "Details du film",
        style = MaterialTheme.typography.titleLarge,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold
    )*/
    LazyColumn(
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/w500" + films.backdrop_path,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = "Image du film" + films.original_title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.8f)
                        .padding(bottom=5.dp)
                )
                Text(
                    text = films.original_title,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            }
        }
        item {
            Row(//Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/w500" + films.poster_path,
                        builder = {
                            crossfade(true)
                            size(400, 300)
                        }
                    ),
                    contentDescription = "Affiche du film" + films.original_title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 15.dp, end = 20.dp, bottom = 15.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(end = 15.dp)
                ) {

                    Text(
                        text = "sorti le " + formatDate(
                            films.release_date,
                            "yyyy-MM-dd",
                            "d MMMM yyyy",
                            Locale.FRANCE
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    )
                    Text(
                        text = getGenres(films.genres),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .padding(top = 15.dp)
                    )
                }
            }
        }
        item{
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(start = 20.dp, end = 15.dp)
            ) {
                Text(
                    text = "Synopsis",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Text(
                    text = films.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                )

            }
        }
        item {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(start = 20.dp, end = 15.dp)
            ) {
                Text(
                    text = "Têtes d'affiche",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )

                /* LazyVerticalGrid(
                 columns = GridCells.Fixed(2),
                 verticalArrangement = Arrangement.spacedBy(16.dp),
                 horizontalArrangement = Arrangement.spacedBy(16.dp)
             ) {
                 items(films) { movie ->
                     ElevatedCard(
                         elevation = CardDefaults.cardElevation(
                             defaultElevation = 6.dp,
                         ),
                         onClick = {
                             navController.navigate("DetailsFilm/${movie.id}")
                         },
                         modifier = Modifier
                             .width(300.dp)
                             .height(380.dp)
                             .padding(8.dp),
                         colors = CardDefaults.cardColors(
                             containerColor = Color.White,
                         )
                     ) {
                         Box(
                             modifier = Modifier
                                 .fillMaxSize()
                             //.height(200.dp)
                         ) {
                             Column(
                                 horizontalAlignment = Alignment.CenterHorizontally,
                                 verticalArrangement = Arrangement.Center
                             ) {
                                 Image(
                                     painter = rememberImagePainter(
                                         data = "https://image.tmdb.org/t/p/w780" + movie.poster_path,
                                         builder = {
                                             crossfade(true)
                                         }
                                     ),
                                     contentDescription = "Image du film" + movie.title,
                                     modifier = Modifier
                                         .width(200.dp)
                                         .height(300.dp)
                                         .padding(
                                             start = 8.dp,
                                             top = 8.dp,
                                             end = 8.dp,
                                             bottom = 0.dp
                                         )
                                 )
                                 Text(
                                     text = movie.title,
                                     style = MaterialTheme.typography.titleLarge,
                                     fontSize = 18.sp,
                                     fontWeight = FontWeight.Bold,
                                     modifier = Modifier
                                     //.padding(top = 2.dp)
                                 )
                                 Text(
                                     text = movie.release_date,
                                     style = MaterialTheme.typography.titleLarge,
                                     fontSize = 12.sp,
                                     fontWeight = FontWeight.Medium,
                                     modifier = Modifier
                                     //.padding(top = 2.dp)
                                 )

                             }
                         }
                     }
                 }
                  }*/
            }
        }
        }
    }

  //  }

//}

@Composable
fun getGenres(genres: List<Genre>): String {
    var genresString = ""
    for (genre in genres) {
        genresString += genre.name + ", "
    }
    return genresString
}

@Composable
fun formatDate(date: String,actualDateFormat: String, newDateFormat: String, locale: Locale): String {
    try {
        val actualDateFormat = SimpleDateFormat(actualDateFormat, locale)
        val newDateFormat = SimpleDateFormat(newDateFormat, locale)
        val formattedDate = actualDateFormat.parse(date)
        if (formattedDate != null) {
            return newDateFormat.format(formattedDate)
        }
    } catch (e: ParseException) {

    }
    return "Date non valide"
}



