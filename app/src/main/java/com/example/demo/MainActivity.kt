package com.example.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.ui.theme.DemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CurrencyScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun CurrencyScreen(modifier: Modifier = Modifier) {
    var weight by remember { mutableStateOf("") }
    val weightAsNumber = weight.toFloatOrNull() ?: 0.0f
    var height by remember { mutableStateOf("") }
    val heightAsNumber = height.toFloatOrNull() ?: 0.0f

    var showDialog by remember { mutableStateOf(false) }
    if (heightAsNumber < 0.0 || weightAsNumber < 0f) {
        showDialog = true
    }

    val bmi = if (heightAsNumber > 0) {
        weightAsNumber / ((heightAsNumber / 100) * (heightAsNumber / 100))
    } else {
        0f
    }

    val fieldModifier: Modifier = Modifier.fillMaxWidth()
    Column(
        modifier = modifier.fillMaxWidth().padding(all = 8.dp),
    ) {
        Text(
            text = "BMI Calculator",
            modifier = fieldModifier,
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Text(text = "Enter weight in kilograms")
        TextField(
            value = weight,
            onValueChange = { weight = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = fieldModifier
        )
        Text(text = "Enter height in centimeters")
        TextField(
            value = height,
            onValueChange = { height = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = fieldModifier
        )
        Text(
            modifier = fieldModifier,
            text = "\nYour BMI:"
        )
        Text(
            modifier = fieldModifier,
            text = if (bmi.isNaN() || bmi.isInfinite()) "0" else bmi.toString()
        )

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Error") },
                text = { Text("Please enter valid numeric values for weight and height!") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoTheme {
        CurrencyScreen()
    }
}


