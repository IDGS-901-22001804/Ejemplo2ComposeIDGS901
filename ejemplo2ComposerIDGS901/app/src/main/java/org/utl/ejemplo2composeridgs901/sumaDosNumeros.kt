package org.utl.ejemplo2composeridgs901

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable

package com.example.ejemplo2composel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun SumaDosNumeros() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var operacion by remember { mutableStateOf("Suma") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Centra los elementos horizontalmente
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho
        )
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // --- SECCIÓN DE RADIO BUTTONS MEJORADA ---

        // Agrupamos la etiqueta y los botones en una Columna para mayor claridad
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Selecciona una operación:")

            // Usamos una Fila para alinear los radio buttons horizontalmente
            Row(
                modifier = Modifier.fillMaxWidth(),
                // 'SpaceAround' distribuye el espacio sobrante alrededor de los elementos.
                // Es una buena opción para que se vea bien en diferentes anchos de pantalla.
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically // Centra todos los elementos verticalmente
            ) {
                val opciones = listOf("Suma", "Resta", "Multiplicación", "División")

                opciones.forEach { op ->
                    // Hacemos que toda la fila (botón + texto) sea clickeable para mejorar la UX
                    Row(
                        modifier = Modifier
                            .clickable(
                                // Opcional: para quitar el efecto visual de "onda" al hacer clic
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { operacion = op },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (operacion == op),
                            onClick = { operacion = op }
                        )
                        Text(
                            text = op,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                val n1 = num1.toDoubleOrNull()
                val n2 = num2.toDoubleOrNull()
                resultado = if (n1 != null && n2 != null) {
                    when (operacion) {
                        "Suma" -> "Resultado: ${n1 + n2}"
                        "Resta" -> "Resultado: ${n1 - n2}"
                        "Multiplicación" -> "Resultado: ${n1 * n2}"
                        "División" -> if (n2 != 0.0) "Resultado: ${n1 / n2}" else "No se puede dividir por cero"
                        else -> "Operación no válida"
                    }
                } else {
                    "Ingrese números válidos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }

        Text(text = resultado)
    }
}