package com.dostonbek.foodadmin.navigation
import android.content.Intent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dostonbek.foodadmin.R
import com.dostonbek.foodadmin.calculation.CalculationScreen
import com.dostonbek.foodadmin.user.UserScreen
import com.dostonbek.foodadmin.food.FoodActivity
import com.dostonbek.foodadmin.home.HomeScreen
import com.dostonbek.foodadmin.message.MessageActivity
import com.dostonbek.foodadmin.order.OrdersScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar() {
    val context= LocalContext.current
    val navController = rememberNavController()
    val items = listOf(
        NavigationItems("Home", R.drawable.home),
        NavigationItems("Order", R.drawable.order),
        NavigationItems("User", R.drawable.user),
        NavigationItems("Calculation",R.drawable.calculation)
    )
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    var expanded by rememberSaveable { mutableIntStateOf(0) }

    val screenTitle = when (selectedItemIndex) {
        0 -> "Home"
        1 -> "Order"
        2 -> "User"
        3-> "Calculation"
        else -> "Home"
    }

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier,
                title = { Text(text = screenTitle) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                actions = {
                    IconButton(onClick = { expanded = 1 }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            modifier = Modifier.size(30.dp),
                            contentDescription = "MoreMenu"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded==1,
                        onDismissRequest = { expanded =0 }, modifier = Modifier
                            .width(160.dp)
                            .height(170.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text("Add New Food") },
                            onClick = {
                                // Handle Option 1 click
                                expanded = 1
                               // navController.navigate("AddFood")
                                val intent=Intent(context,FoodActivity::class.java)
                                context.startActivity(intent)

                            }
                        )
                        DropdownMenuItem(
                            text = { Text(" Make a Prize") },
                            onClick = {
                                // Handle Option 2 click
                                expanded = 1
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Send Message") },
                            onClick = {
                                // Handle Option 2 click
                                expanded = 1
                                val intent=Intent(context,MessageActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                        // Add more menu items here as needed
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                modifier = Modifier
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            val route = when (selectedItemIndex) {
                                0 -> "Home"
                                1 -> "Order"
                                2 -> "User"
                                3->"Calculation"
                                else -> "Home"
                            }
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.logo),
                                
                                contentDescription = item.name,
                                tint = if (selectedItemIndex == index) {
                                    Color.Black
                                } else {
                                    Color.Black
                                },
                                modifier = Modifier.size(
                                    if (selectedItemIndex == index) {
                                        25.dp
                                    } else {
                                        20.dp
                                    }
                                )
                            )
                        },
                        label = { Text(text = item.name) }
                    )
                }
            }
        }
    ) { innerPadding ->
        navController.addOnDestinationChangedListener { _, destination, _ ->
            selectedItemIndex = when (destination.route) {
                "Home" -> 0
                "Order" -> 1
                "User" -> 2
                "Calculation"->3
                else -> 0
            }
        }
        NavHost(
            navController = navController,
            startDestination = "Home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("Home") { HomeScreen() }
            composable("Order") { OrdersScreen() }
            composable("User") { UserScreen() }
            composable("Calculation"){ CalculationScreen()}
        }
    }
}
@Preview(showBackground = true)
@Composable
fun NavigationBarPreview(){
    NavigationBar()
}
