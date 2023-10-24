package com.example.tp2premiereapplicationandroid

import android.util.Log
import androidx.compose.foundation.Image

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

//Barre de navigation en mode portrait
@Composable
fun BarreNavigation(navController: NavController) {
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
                onClick = { selectedItem = index
                    navController.navigate(item)
                }
            )
        }
    }
}

//Barre de navigation en mode paysage
@Composable
fun BarreNavigationPaysage(navController: NavController) {
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
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(onClick = { selectedItem = index
                            navController.navigate(item)})
                ) {
                    Column (
                        modifier = Modifier
                            .padding(start=8.dp),
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

//Barre de recherche en mode portrait
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
            Log.d("BarreRecherche", "Mode portrait")
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
                trailingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Icône de recherche"
                    )
                },
            ) {

            }
        }
    }

//Barre de recherche en mode paysage
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarreRecherchePaysage( viewModel: MainViewModel,
                           onSearchClick: (text: String) -> Unit,){

    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    var showSearchBar by remember { mutableStateOf(false) }

    FloatingActionButton(
        onClick = {
            //onSearchClick(text)
            showSearchBar = true
        },
        containerColor= Color.Black,
        modifier = Modifier
            .padding(16.dp)

        ) {
        Image(
            painterResource(id = R.drawable.baseline_search_24),
            contentDescription = "Icône de temps",
            modifier = Modifier.size(20.dp),
        )
        if (showSearchBar) { //si on clique sur le bouton de recherche, on affiche la barre de recherche
            SearchBar(
                modifier = Modifier
                    .semantics { traversalIndex = -1f },
                query = text,
                onQueryChange = { text = it },
                onSearch = {
                    active = false
                    onSearchClick(it)
                    showSearchBar= false
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text("Rechercher des films, des séries, des acteurs") },
                trailingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Icône de recherche"
                    )
                },
            ){
            }

        }
    }
}