package com.dostonbek.foodadmin.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dostonbek.foodadmin.navigation.NavigationBar
import com.dostonbek.foodadmin.ui.theme.FoodAdminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAdminTheme {

                NavigationBar()


            }
        }
    }
}
