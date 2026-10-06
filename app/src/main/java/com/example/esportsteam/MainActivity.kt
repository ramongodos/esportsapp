package com.example.esportsteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esportsteam.ui.theme.MyApplicationTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ){
                Text(
                    text = "TEAM TITANS",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 16.dp)
                        .align (Alignment.CenterHorizontally)
                )

                //Primero
                Jugador(
                    nombre = "xPeke",
                    rol = "mid",
                    edad = 34,
                    nivel= 34,
                    imagen = R.drawable.xpeke
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                //Segundo
                Jugador(
                    nombre = "Epoxie",
                    rol = "jungle",
                    edad = 23,
                    nivel= 23,
                    imagen = R.drawable.jugador2
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                //Tercero
                Jugador(
                    nombre = "Sancho",
                    rol = "support",
                    edad = 28,
                    nivel= 28,
                    imagen = R.drawable.jugador3
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                //Cuarto
                Jugador(
                    nombre = "August",
                    rol = "damage carry",
                    edad = 30,
                    nivel= 30,
                    imagen = R.drawable.jugador4
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )




            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun JugadorPreview(){
    Jugador(
    nombre = "xPeke",
    rol = "mid",
    edad = 32,
    nivel = 55,
    imagen = R.drawable.xpeke
    )

}


@Composable
fun Jugador(
    nombre: String,
    rol: String,
    edad: Int,
    nivel: Int,
    @DrawableRes imagen: Int
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = imagen),
            contentDescription = "Foto de $nombre",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column{
            Text(
                text = nombre,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Rol = $rol",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Edad = $edad años",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Nivel = $nivel",
                style = MaterialTheme.typography.bodySmall
            )

        }
    }
}

