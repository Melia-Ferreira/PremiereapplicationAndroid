package com.example.tp2premiereapplicationandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import com.example.tp2premiereapplicationandroid.ui.theme.TP2PremiereApplicationAndroidTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP2PremiereApplicationAndroidTheme {
                    val windowSizeClass = calculateWindowSizeClass(this)
                val navController = rememberNavController()
                val viewModel : MainViewModel by viewModels()

                NavHost(
                    navController = navController,
                    startDestination = "Film"
                ){
                    composable("Profil"){
                        Profil(navController, windowSizeClass)
                    }
                    composable("Film"){
                        Film(navController, windowSizeClass, viewModel )
                    }
                    composable("DetailsFilm/{filmID}"){ backStackEntry ->
                        val filmID = backStackEntry.arguments?.getString("filmID") ?: ""
                        DetailsFilm(navController, windowSizeClass, viewModel, filmID)
                    }
                }
            }
            }
        }
    }



