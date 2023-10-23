package com.example.tp2premiereapplicationandroid

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Profil( navController: NavController,
    windowClass: WindowSizeClass) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF142949))
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
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
                    Bouton(navController)
                }
            }

    } else -> {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF142949))
        ) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    MonImage()
                    Spacer(modifier = Modifier.height(15.dp)) //espace
                    Texte()
                    Spacer(modifier = Modifier.height(30.dp)) //espace
                }
                Spacer(modifier = Modifier.width(50.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Reseaux()
                    Spacer(modifier = Modifier.height(30.dp)) //espace
                    Bouton(navController)
                }
            }
        }
    }
}
            }


@Composable
fun Texte() {
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "Mélia Ferreira",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Étudiante en FIE4",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 15.sp,
            color = Color.White
        )
        Text(
            text = "École d'ingénieurs ISIS Castres",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 15.sp,
            color = Color.White
        )

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
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                fontWeight = FontWeight.Light,
                color = Color.White
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
                fontWeight = FontWeight.Light,
                color = Color.White
            )
        }
    }

}

@Composable
fun Bouton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("Films")
        },
        colors = ButtonDefaults.buttonColors(Color.White)
    ) {
        Text(
            text = "Démarrer",
            color = Color(0xFF142949),
            fontSize = 23.sp,

        )
    }
}
