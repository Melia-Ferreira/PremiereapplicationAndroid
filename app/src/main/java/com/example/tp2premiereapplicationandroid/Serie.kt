package com.example.tp2premiereapplicationandroid

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Serie(navController: NavController,
         windowClass: WindowSizeClass,
         viewModel: MainViewModel
) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            val configuration = LocalConfiguration.current
            val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            Log.v("orientation", "orientation:" + configuration.orientation)
            Scaffold(
                topBar = {
                    /* ToolBar(
                     searchQuery = "",
                     onSearchClick = {
                         viewModel.getFilmsRecherche(query = it)
                     },
                     searchActive = false,
                     viewModel = viewModel) */
                    BarreRecherche(
                        viewModel,
                        onSearchClick = {
                            viewModel.getFilmsRecherche(query = it)
                        })
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
                        ListeSeriesPopulaire(navController, windowClass, viewModel)
                    }
                }
            )
        } else -> {
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            LeftBarreNavigationChat()
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                ListeSeriesPopulaire(navController, windowClass, viewModel, nbColonne = 3)

                Log.v("orientation", "orientation:" + configuration.orientation)
                BarreRecherche(viewModel,
                    onSearchClick = {
                        viewModel.getFilmsRecherche(query = it)
                    })
            }
        }
    }
    }
}

/*topBar = {
     ToolBar(
     searchQuery = "",
     onSearchClick = {
         viewModel.getFilmsRecherche(query = it)
     },
     searchActive = false,
     viewModel = viewModel) */
/*FloatingActionButton(onClick = {
    BarreRecherche(
        viewModel,
        onSearchClick = {
            viewModel.getFilmsRecherche(query = it)
        } ) }) {

}*/ /*
                BarreRecherche(
                    viewModel,
                    onSearchClick = {
                        viewModel.getFilmsRecherche(query = it)
                    })*/
/*    },

    bottomBar = {
        LeftBarreNavigationChat()
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
}
} */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListeSeriesPopulaire(navController: NavController,
                        windowClass: WindowSizeClass,
                        viewmodel: MainViewModel,
                        nbColonne: Int=2) {

    val series by viewmodel.series.collectAsState()
    LaunchedEffect(true) {
        viewmodel.getSeriesInitiales()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(nbColonne),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(series) { movie ->
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp,
                ),
                onClick = {
                   // navController.navigate("DetailsFilm/${movie.id}")
                },
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
                                data = "https://image.tmdb.org/t/p/w780" + serie.poster_path,
                                builder = {
                                    crossfade(true)
                                }
                            ),
                            contentDescription = "Image du film" + serie.original_name,
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
                            text = serie.title,
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                            //.padding(top = 2.dp)
                        )
                        Text(
                            text = formatDate(
                                serie.release_date,
                                "yyyy-MM-dd",
                                "dd-MM-yyyy",
                                Locale.FRANCE
                            ),
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
