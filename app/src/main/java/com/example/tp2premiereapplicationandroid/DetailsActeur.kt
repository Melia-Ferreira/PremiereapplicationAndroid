package com.example.tp2premiereapplicationandroid

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
fun DetailsActeur(navController: NavController,
                windowClass: WindowSizeClass,
                viewModel: MainViewModel,
                acteurid: String
) {
    val acteur by viewModel.acteur.collectAsState()
    LaunchedEffect(true) {
        viewModel.getDetailActeur(acteurid)
    }
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Box(
                modifier = Modifier
                    .background(Color(0xFF142949)),
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item(span = {
                        GridItemSpan(2)
                    }) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ProfilActeur(viewModel, acteurid)
                        }
                    }
                    item(span = {
                        GridItemSpan(2)
                    }) {
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            PresentationFilm(viewModel, acteurid)
                        }
                    }
                    /* item(span = {
                         GridItemSpan(2)
                     }) {
                         Filmographie()
                     }
                     items(films.credits.cast) { film ->
                     ElevatedCard(
                         elevation = CardDefaults.cardElevation(
                             defaultElevation = 6.dp,
                         ),
                         onClick = {
                             //navController.navigate("DetailsPersonne/${acteur.id}")
                         },
                         modifier = Modifier
                             .width(210.dp)
                             .height(380.dp)
                             .padding(8.dp),
                         colors = CardDefaults.cardColors(
                             containerColor = Color.White,
                         )
                     ) {
                         Box(
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .fillMaxHeight()
                             //.height(200.dp)
                         ) {
                             Column(
                                 horizontalAlignment = Alignment.CenterHorizontally,
                                 verticalArrangement = Arrangement.Center
                             ) {
                                 Image(
                                     painter = rememberImagePainter(
                                         data = "https://image.tmdb.org/t/p/w780" + films.poster_path,
                                         builder = {
                                             crossfade(true)
                                         }
                                     ),
                                     contentDescription = "Image" + films.title,
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
                                     text = films.title,
                                     style = MaterialTheme.typography.titleLarge,
                                     fontSize = 18.sp,
                                     fontWeight = FontWeight.Bold,
                                     modifier = Modifier
                                     //.padding(top = 2.dp)
                                 )
                             }
                         }
                     }
                 }*/
                }
            }
        }

        else -> {
            Box(
                modifier = Modifier
                    .background(Color(0xFF142949)),
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item(span = {
                        GridItemSpan(4)
                    }) {
                        ProfilActeur(viewModel, acteurid)
                    }
                    item(span = {
                        GridItemSpan(4)
                    }) {
                        Row(
                            //Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Biographie(viewModel, acteurid)
                        }
                    }
                    item(span = {
                        GridItemSpan(4)
                    }) {
                        Filmographie()
                    }
                    //afficher la filmographie de chaque acteur

                    /* items(acteur.credits.cast) { cast ->
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp,
                        ),
                        onClick = {
                            //navController.navigate("DetailsPersonne/${cast.id}")
                        },
                        modifier = Modifier
                            .width(210.dp)
                            .height(380.dp)
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                            //.height(200.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = rememberImagePainter(
                                        data = "https://image.tmdb.org/t/p/w780" + cast.poster_path,
                                        builder = {
                                            crossfade(true)
                                        }
                                    ),
                                    contentDescription = "Image" + acteur.credits.cast.title,
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
                                    text = films.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                    //.padding(top = 2.dp)
                                )
                            }
                        }
                    }
                }*/
                }
            }
        }
    }
}
@Composable
fun ProfilActeur(viewModel: MainViewModel, acteurid: String){
    val acteur by viewModel.acteur.collectAsState()
    LaunchedEffect(true) {
        viewModel.getDetailActeur(acteurid)
    }
    Image(
        painter = rememberImagePainter(
            data = "https://image.tmdb.org/t/p/w500" + acteur.profile_path,
            builder = {
                crossfade(true)
                //size(800, 400)
            }
        ),
        contentDescription = "Image du film" + acteur.name,
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.8f)
            .height(200.dp)

    )
    Text(
        text = acteur.name,
        style = MaterialTheme.typography.titleLarge,
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
    )

}
@Composable
fun Biographie(viewModel: MainViewModel, acteurid: String){
    val acteur by viewModel.acteur.collectAsState()
    LaunchedEffect(true) {
        viewModel.getDetailActeur(acteurid)
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(end = 15.dp)
    ) {
        Text(
            text = "Biographie",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Row(
            modifier= Modifier
                .padding(bottom=15.dp)
        ) {
            Image(
                painterResource(id = R.drawable.baseline_calendar_month_24),
                contentDescription = "Icône de calendrier",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Né le" + formatDate(
                    acteur.birthday,
                    "yyyy-MM-dd",
                    "d MMMM yyyy",
                    Locale.FRANCE
                ),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )
        }
        Row(
            modifier= Modifier
                .padding(bottom=15.dp)
        ) {
            Image(
                painterResource(id = R.drawable.baseline_location_on_24),
                contentDescription = "Icône de lieu",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = " Né à ${acteur.place_of_birth}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )
        }
            Text(
                text = acteur.biography,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )

    }
}
@Composable
fun Filmographie(){
    Text(
        text = "Films :",
        style = MaterialTheme.typography.titleMedium,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .padding(start = 20.dp, end = 15.dp, top = 15.dp)
    )
}
