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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
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
            HomeScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun HomeScreenPreview() {
        HomeScreen()

    }


    @Composable
    fun mostrarJugador(
        jugador: Jugador,
        modificarJugador: () -> Unit,
        eliminarJugador:() -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = jugador.imagen),
                contentDescription = "Foto de ${jugador.nombre}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = jugador.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Rol = ${jugador.rol}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Nickname = ${jugador.nickname}",
                    style = MaterialTheme.typography.bodySmall
                )

                Row{
                    Button(onClick = {eliminarJugador()}) {Text("Eliminar") }
                    Spacer(Modifier.size(5.dp))
                    Button(onClick = {modificarJugador()}){Text ("Modificar")}
                }

            }
        }
    }


    @Composable
    fun HomeScreen() {

        val listaJugador = remember {
            mutableStateListOf(
                Jugador(
                    nombre = "xPeke",
                    rol = "mid",
                    nickname = "xPeke",
                    imagen = R.drawable.xpeke
                ),
                Jugador(
                    nombre = "Rodolfo Andes",
                    rol = "jungle",
                    nickname = "Epoxie",
                    imagen = R.drawable.jugador2
                ),
                Jugador(
                    nombre = "Umberto Frigodedo",
                    rol = "support",
                    nickname = "Sancho",
                    imagen = R.drawable.jugador3
                ),
                Jugador(
                    nombre = "Eustaquio Habichuela",
                    rol = "damage carry",
                    nickname = "TakiTaki",
                    imagen = R.drawable.jugador4
                ),
                Jugador(
                    nombre = "Federico Picapiedra",
                    rol = "suplente",
                    nickname = "Pedro Stone",
                    imagen = R.drawable.jugador5
                )
            )
        }

        var jugadorAEliminar by remember { mutableStateOf<Jugador?>(null) }
        var jugadorAEditar  by remember {mutableStateOf<Jugador?>(null)}

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            Text(
                text = "TEAM TITANS",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(listaJugador) { jugador ->
                    mostrarJugador(jugador = jugador,
                        eliminarJugador = {
                            jugadorAEliminar = jugador},
                        modificarJugador = {
                            jugadorAEditar = jugador
                        })
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }
            }

            formulario (
                jugadorAEditar = jugadorAEditar,
                onGuardar = { jugadorNuevo ->
                    if (jugadorAEditar == null) {
                        listaJugador.add(jugadorNuevo)
                    } else {
                        val index = listaJugador.indexOf(jugadorAEditar)
                        if(index != -1){
                            listaJugador[index] = jugadorNuevo.copy(
                            id = jugadorAEditar!!.id,
                            imagen = jugadorAEditar!!.imagen
                            )
                        }
                        jugadorAEditar = null
                    }
                },
                onCancelar = {
                    jugadorAEditar = null
                })

        }

            jugadorAEliminar?.let { jugadorActual ->
                AlertDialog(
                    onDismissRequest = { jugadorAEliminar = null },
                    title = { Text("Eliminar jugador") },
                    text = { Text("¿Estás seguro de eliminar a ${jugadorActual.nombre}?") },
                    confirmButton = {
                        TextButton(
                            onClick = {

                                listaJugador.remove(jugadorActual)

                                jugadorAEliminar = null
                            }
                        ) {
                            Text("Eliminar", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                jugadorAEliminar = null
                            }
                        ) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }


        }



data class Jugador(
    val id: Int = generarIdUnico(),
    val nombre: String,
    val nickname: String,
    val rol: String,
    @DrawableRes val  imagen: Int
){
    companion object{
        private var contadorID = 1
        fun generarIdUnico():Int{
            return contadorID++
        }
    }
}

@Composable
fun formulario(onGuardar: (Jugador) -> Unit,
jugadorAEditar: Jugador? = null,
               onCancelar: () -> Unit){

    var nombre by remember(jugadorAEditar)  { mutableStateOf(jugadorAEditar?.nombre ?:"")}
    var rol by remember(jugadorAEditar) { mutableStateOf(jugadorAEditar?.rol ?: "") }
    var nickname by remember(jugadorAEditar) { mutableStateOf(jugadorAEditar?.nickname?:"") }
    Column(modifier = Modifier.padding(top = 10.dp)
        .fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        Spacer(Modifier.height(5.dp))
        TextField(
            value = rol,
            onValueChange = { rol = it },
            label = { Text("Rol") }
        )
        Spacer(Modifier.height(5.dp))
        TextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("Nickname") }
        )
        Spacer(Modifier.height(5.dp))
        Row {
            Button(
                    onClick = {
                        if (nombre.isNotBlank() && rol.isNotBlank() && nickname.isNotBlank()) {
                            onGuardar(
                                Jugador(
                                    nombre = nombre,
                                    rol = rol,
                                    nickname = nickname,
                                    imagen = R.drawable.jugador
                                )
                            )
                        }
                    },
                ) { if (jugadorAEditar == null) Text("Añadir jugador")
                else Text("Guardar cambios") }
            }
            Spacer(Modifier.size(5.dp))
            Button(onClick = {
                nombre = ""
                rol = ""
                nickname = ""
                onCancelar()
            }) { Text("Cancelar") }
        }

    }






