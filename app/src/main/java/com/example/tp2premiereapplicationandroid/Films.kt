package com.example.tp2premiereapplicationandroid

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import coil.compose.rememberImagePainter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Films(navController: NavController,
           windowClass: WindowSizeClass,
         viewModel: MainViewModel
) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    BarreRecherche(
                        viewModel,
                        onSearchClick = {
                            viewModel.getFilmsRecherche(query = it)
                        })
                },

                bottomBar = {
                    BarreNavigation(navController)
                },
                content = {
                    Box(
                        modifier = Modifier
                            .padding(it) // Utilisez contentPadding pour définir la marge intérieure
                            .fillMaxWidth()
                    ) {
                        ListeFilmsPopulaire(navController, windowClass, viewModel)
                    }
                }
            )
        } else -> {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
            BarreNavigationPaysage(navController)
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                ListeFilmsPopulaire(navController, windowClass, viewModel, nbColonne = 3)

                BarreRecherchePaysage(viewModel,
                    onSearchClick = {
                        viewModel.getFilmsRecherche(query = it)
                    })
            }
        }
        }
    }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListeFilmsPopulaire(navController: NavController,
                        windowClass: WindowSizeClass,
                        viewmodel: MainViewModel,
                        nbColonne: Int=2) {

    val movies by viewmodel.movies.collectAsState()
    LaunchedEffect(true) {
        viewmodel.getFilmsInitiaux()
    }

            LazyVerticalGrid(
                columns = GridCells.Fixed(nbColonne),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(movies) { movie ->
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
                            containerColor = Color(0xFF142949),
                        )
                    ){
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
                            text = movie.title.takeWhile { it != ':' },
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = formatDate(
                                movie.release_date,
                                "yyyy-MM-dd",
                                "dd-MM-yyyy",
                                Locale.FRANCE
                            ),
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )

                    }
                }

            }
        }
    }
}
