package com.example.bmi_aplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.material3.darkColorScheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinimalisticAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Custom Theme Colors
private val DarkBlue = Color(0xFF003F9F)
private val DarkRed = Color(0xFF8B0000)
private val GrayishBlack = Color(0xFF1C1C1C)

// Main Content
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val input = remember { mutableStateOf("") }
    val output = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(GrayishBlack),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input Field
        OutlinedTextField(
            value = input.value,
            onValueChange = { input.value = it },
            label = { Text("Enter a value", color = DarkBlue) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = DarkBlue,
                unfocusedBorderColor = DarkRed,
                cursorColor = DarkBlue,
                focusedLabelColor = DarkBlue,
                unfocusedLabelColor = DarkRed
                // Text color comes from the default theme, override globally if needed
            )
        )



        // Button
        Button(
            onClick = { output.value = "You entered: ${input.value}" },
            colors = ButtonDefaults.buttonColors(containerColor = DarkRed),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Submit", color = Color.White, fontWeight = FontWeight.Bold)
        }

        // Output Text
        Text(
            text = output.value,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

// Minimalistic App Theme
@Composable
fun MinimalisticAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = DarkBlue,
            secondary = DarkRed,
            background = GrayishBlack,
            onBackground = Color.White,
            onPrimary = Color.White
        ),
        typography = Typography(),
        content = content
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    MinimalisticAppTheme {
        MainContent()
    }
}
