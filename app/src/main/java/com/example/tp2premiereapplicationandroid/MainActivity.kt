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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP2PremiereApplicationAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Screen()
                }
            }
        }
    }
}
@Composable
fun Screen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        MonImage()
        Spacer(modifier = Modifier.height(15.dp)) //espace
        Texte()
        Spacer(modifier = Modifier.height(30.dp)) //espace
        Row{
            Reseaux()
        }
        Spacer(modifier = Modifier.height(30.dp)) //espace
        Bouton()
    }

}
@Composable
fun Texte() {
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "Mélia Ferreira",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Étudiante en FIE4",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 25.sp)
        Text(
            text = "École d'ingénieurs ISIS Castres",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 25.sp)

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


