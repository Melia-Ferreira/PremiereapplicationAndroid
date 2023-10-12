package com.example.tp2premiereapplicationandroid

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Film(navController: NavController,
           windowClass: WindowSizeClass,
         viewModel: MainViewModel
){
    Scaffold(
        topBar = {
          BarreRecherche(
              viewModel,
              onSearchClick = {
                  viewModel.getFilmsRecherche(query = it)
              }
          )
        },

        bottomBar = {
            BarreNavigation()
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
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarreRecherche( viewModel: MainViewModel,
                    onSearchClick: (text: String) -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = -1f },
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                active = false
                onSearchClick(it)
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text("Rechercher des films, des séries, des acteurs") },
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Icône de recherche" )},
                ){

        }
      /*  Button(
            onClick = {
                onSearchClick()
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .semantics { traversalIndex = 1f },
        ){
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Rechercher",
                modifier = Modifier.size(24.dp)
            )
        } */
    }
}

@Composable
fun BarreNavigation() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Films", "Séries", "Acteurs")
    val icons = listOf(
        painterResource(id = R.drawable.baseline_movie_creation_24),
        painterResource(id = R.drawable.baseline_tv_24),
        painterResource(id = R.drawable.baseline_person_24)
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Image(painter = icons[index], contentDescription = "Icône de film") },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListeFilmsPopulaire(navController: NavController,
                        windowClass: WindowSizeClass,
                        viewmodel: MainViewModel) {

    val movies by viewmodel.movies.collectAsState()
    LaunchedEffect(true) {
        viewmodel.getFilmsInitiaux()
    }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(movies) { movie ->
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp,
                        ),
                        onClick = {navController.navigate("DetailsFilm") },
                        modifier = Modifier
                            .width(300.dp)
                            .height(380.dp)
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
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
    }
}
