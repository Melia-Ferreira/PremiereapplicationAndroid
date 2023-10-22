package com.example.tp2premiereapplicationandroid

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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter
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

@Composable
fun LeftBarreNavigation() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Films", "Séries", "Acteurs")
    val icons = listOf(
        painterResource(id = R.drawable.baseline_movie_creation_24),
        painterResource(id = R.drawable.baseline_tv_24),
        painterResource(id = R.drawable.baseline_person_24)
    )

    NavigationBar {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable(onClick = { selectedItem = index })
                ) {
                    Icon(
                        painter = icons[index],
                        contentDescription = "Icône",
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp),
                        /*icon = { Image(painter = icons[index], contentDescription = "Icône de film") },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }*/
                    )
                    Text(
                        text=item,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(top = 5.dp),
                        textAlign= TextAlign.Center
                    )
                }

            }
        }
    }
}

@Composable
fun LeftBarreNavigationChat() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Films", "Séries", "Acteurs")
    val icons = listOf(
        painterResource(id = R.drawable.baseline_movie_creation_24),
        painterResource(id = R.drawable.baseline_tv_24),
        painterResource(id = R.drawable.baseline_person_24)
    )

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .width(100.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        items.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { selectedItem = index }
                ) {
                    Column (
                        modifier = Modifier
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                    Icon(
                        painter = icons[index],
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        text = item,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.
                        padding(top = 4.dp)
                    )
                }
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun ToolBar(
    searchQuery: String,
    onSearchClick: (text: String) -> Unit,
    searchActive: Boolean,
    viewModel: MainViewModel
) {
    Column {
        TopAppBar(
            title = {
                Text(text = "Fav'App")
            },
            actions = {
                if (!searchActive) {
                    IconButton(onClick = {
                        onSearchClick(searchQuery)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Rechercher"
                        )
                    }

                }
            },
        )
        if (searchActive) {
            BarreRecherche(
                viewModel,
                onSearchClick = {
                    viewModel.getFilmsRecherche(query = it)
                }
            )
        }
    }
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
        /*ToolBar(
             searchQuery = text,
             onSearchClick = {
                             text=it
                 active=!active
                 onSearchClick(it)
             },
             searchActive = active
         ) */
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
    }
}
