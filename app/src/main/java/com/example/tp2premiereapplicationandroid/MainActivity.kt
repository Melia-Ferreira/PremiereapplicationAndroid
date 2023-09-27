package com.example.tp2premiereapplicationandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() { //Définit une classe appelée MainActivity qui étend ComponentActivity. ComponentActivity est une classe de base fournie par Jetpack Compose pour les activités Android. Cette classe est utilisée pour gérer le cycle de vie de l'activité.

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class) // Cette annotation indique que l'activité utilise une API expérimentale. En d'autres termes, elle signale que des fonctionnalités ou des bibliothèques expérimentales sont utilisées dans cette activité.
    override fun onCreate(savedInstanceState: Bundle?) { //Ceci est une fonction onCreate qui est appelée lorsque l'activité est créée. Elle prend en paramètre un objet Bundle, qui est généralement utilisé pour restaurer l'état précédent de l'activité si nécessaire.
        super.onCreate(savedInstanceState) //Appelle la fonction onCreate de la classe parent (ComponentActivity) pour effectuer toute initialisation nécessaire.
        setContent { //Cette ligne définit le contenu de l'activité à l'aide de Jetpack Compose. Il spécifie la structure de l'interface utilisateur de l'activité.
            TP2PremiereApplicationAndroidTheme { //Cela fait référence à un thème (style) défini pour l'application Android. Le contenu de l'activité sera stylisé en fonction de ce thème.
                    val windowSizeClass = calculateWindowSizeClass(this) //Cette ligne appelle une fonction calculateWindowSizeClass pour déterminer la classe de taille de la fenêtre de l'appareil. Cela peut être utile pour adapter l'interface utilisateur en fonction de la taille de l'écran ou de l'appareil.
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "Profil"
                ){
                    composable("Profil"){
                        Profil(navController, windowSizeClass)
                    }
                    composable("Film"){
                        Film(navController, windowSizeClass)
                    }
                }
            }
            }
        }
    }



