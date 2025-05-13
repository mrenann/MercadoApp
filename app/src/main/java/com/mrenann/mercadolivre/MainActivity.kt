package com.mrenann.mercadolivre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.mrenann.mercadolivre.homeScreen.presentation.screens.HomeScreen
import com.mrenann.mercadolivre.ui.theme.MercadoLivreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MercadoLivreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                    ) {
                        Navigator(
                            HomeScreen(),
                        )
                    }
                }
            }
        }
    }
}