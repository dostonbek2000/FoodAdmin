package com.dostonbek.foodadmin.food

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dostonbek.foodadmin.ui.theme.LightTextColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFood(foodViewModel: FoodViewModel = viewModel()) {
    val names = listOf("Nothing", "Hot Dog", "Pizza", "Hamburger")
    val prices = listOf("Nothing", "10000 so'm", "20000 so'm", "30000 so'm")
    val dropIcon = Icons.Default.KeyboardArrowDown
    val upIcon = Icons.Default.KeyboardArrowUp
    val context = LocalContext.current

    var selectedName by remember { mutableStateOf(names.first()) }
    var selectedPrice by remember { mutableStateOf(prices.first()) }

    var isNamePopupVisible by remember { mutableStateOf(false) }
    var isPricePopupVisible by remember { mutableStateOf(false) }

    val status by foodViewModel.status.observeAsState("")

    val imageUrl = when (selectedName) {
        "Hot Dog" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJfil_12iDPKI6VUiqFqLBG8FpVdN_PzpAnA&s"
        "Pizza" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1MhFSYjDIxeMTTXlUbBF6t1oPDE_ccPmPjQ&s"
        else -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTa7fFCv_qcHetLKqUDiJ1dl_LtIgfTO7ypw&s"
    }


    Scaffold(topBar = { TopAppBar(title = { Text(text = "Add New Food")}, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White, titleContentColor = LightTextColor))}) { innerPadding->
      Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
    ) {

        Text(text = "Ovqatni tanlang", fontSize = 16.sp, color = LightTextColor)
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()

                .border(1.dp, Color.Gray)
                .clickable {
                    isNamePopupVisible = !isNamePopupVisible
                    isPricePopupVisible = false
                }
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = selectedName, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        isNamePopupVisible = !isNamePopupVisible
                        isPricePopupVisible = false
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    if (!isNamePopupVisible) {


                        Icon(imageVector = dropIcon, contentDescription = "Dropdown")
                    } else {
                        Icon(imageVector = upIcon, contentDescription = "UpDown")
                    }

                }
            }
        }

        if (isNamePopupVisible) {
            Popup(

                properties = PopupProperties(dismissOnClickOutside = true),

                ) {
                Card(
                    modifier = Modifier
                        .width(160.dp)
                        .height(170.dp)

                        .padding(8.dp, 4.dp, 8.dp, 4.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    names.forEach { name ->
                        Text(
                            text = name,
                            modifier = Modifier.fillMaxWidth()
                                .clickable {
                                    selectedName = name
                                    isNamePopupVisible = false
                                }
                                .padding(8.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Narxni tanlang", fontSize = 16.sp, color = LightTextColor)
        Spacer(modifier = Modifier.height(8.dp))
        // Price Dropdown Popup
        Box(
            modifier = Modifier
                .fillMaxWidth()

                .border(1.dp, Color.Gray)
                .clickable {
                    isPricePopupVisible = !isPricePopupVisible
                    isNamePopupVisible = false
                }
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = selectedPrice, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        isPricePopupVisible = !isPricePopupVisible
                        isNamePopupVisible = false
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    if (!isPricePopupVisible) {


                        Icon(imageVector = dropIcon, contentDescription = "Dropdown")
                    } else {
                        Icon(imageVector = upIcon, contentDescription = "Dropdown")


                    }

                }
            }
        }

        if (isPricePopupVisible) {
            Popup(

                properties = PopupProperties(dismissOnClickOutside = true)
            ) {
                Card(
                    modifier = Modifier
                        .width(160.dp)
                        .height(170.dp)
                        .padding(8.dp, 4.dp, 8.dp, 4.dp),
                    elevation = CardDefaults.cardElevation(10.dp)


                ) {
                    prices.forEach { price ->
                        Text(
                            text = price,
                            modifier = Modifier.fillMaxWidth()
                                .clickable {
                                    selectedPrice = price
                                    isPricePopupVisible = false
                                }
                                .padding(8.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (selectedName != "Nothing" && selectedPrice != "Nothing") {
                    val priceValue = selectedPrice.replace(" so'm", "")
                    foodViewModel.addFood(selectedName, priceValue, imageUrl)
                } else {
                    Toast.makeText(context, "Malumotlarni kiriting", Toast.LENGTH_SHORT).show()
                }

            },
            modifier = Modifier.fillMaxWidth(), colors = ButtonColors(containerColor = LightTextColor, contentColor = Color.White, disabledContentColor = Color.Black, disabledContainerColor = Color.Black)
        ) {
            Text("Add Food Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = status)
    }}}
}

@Preview(showBackground = true)
@Composable
fun AddFoodPreview() {
    AddFood()
}
