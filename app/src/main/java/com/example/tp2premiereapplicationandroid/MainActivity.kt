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

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP2PremiereApplicationAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val windowSizeClass = calculateWindowSizeClass(this)
                    Screen(windowSizeClass)
                }
            }
        }
    }
}
@Composable
fun Screen(windowClass:WindowSizeClass) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                MonImage()
                Spacer(modifier = Modifier.height(15.dp)) //espace
                Texte()
                Spacer(modifier = Modifier.height(30.dp)) //espace
                Row {
                    Reseaux()
                }
                Spacer(modifier = Modifier.height(30.dp)) //espace
                Bouton()
            }
        }

        else -> {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = CenterHorizontally
                    )
                            {
                    MonImage()
                    Spacer(modifier = Modifier.height(15.dp)) //espace
                    Texte()
                    Spacer(modifier = Modifier.height(30.dp)) //espace
                }
                Spacer(modifier = Modifier.width(50.dp))
                Column(
                    horizontalAlignment = CenterHorizontally
                ) {
                    Reseaux()
                    Spacer(modifier = Modifier.height(30.dp)) //espace
                    Bouton()
                }
            }
        }
    }
}
@Composable
fun Texte() {
    Column (horizontalAlignment = CenterHorizontally){
        Text(
            text = "Mélia Ferreira",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Étudiante en FIE4",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 15.sp)
        Text(
            text = "École d'ingénieurs ISIS Castres",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 15.sp)

}
}
@Composable
fun MonImage(){
    Column() {
        Image(
            painterResource(id = R.drawable.moi),
            contentDescription = "Photo de profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)

        )
    }
}

@Composable
fun Reseaux(){
    Column(horizontalAlignment = CenterHorizontally) {
        Row() {
            Image(
                painterResource(id = R.drawable.gmail),
                contentDescription = "Icône de GMail",
                modifier = Modifier.size(20.dp)

            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "meliaferreira12@gmail.com",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
        }
        Row() {
            Image(
                painterResource(id = R.drawable.linkedin),
                contentDescription = "Icône de Linkedin",
                modifier = Modifier.size(20.dp)

            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "linkedin.com/in/melia-ferreira-37770b209",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
        }
    }

}

@Composable
fun Bouton() {
    Button(onClick ={}){
        Text(
            text = "Démarrer"
        )
    }
}


