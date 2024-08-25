package com.dostonbek.foodadmin.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.dostonbek.foodadmin.R
import com.dostonbek.foodadmin.data.Food
import com.dostonbek.foodadmin.food.FoodViewModel
import com.dostonbek.foodadmin.ui.theme.LightBackgroundColor
import com.dostonbek.foodadmin.ui.theme.LightPriceColor
import com.dostonbek.foodadmin.ui.theme.LightTextColor

@Composable
fun HomeScreen(modifier: Modifier = Modifier,foodViewModel: FoodViewModel = viewModel()) {
    val foodItems by foodViewModel.foodItems.observeAsState(emptyList())
    val status by foodViewModel.status.observeAsState("")
    LaunchedEffect(Unit) {
        foodViewModel.readFood()
    }
    Column(
        modifier = modifier.background(LightBackgroundColor)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Set the number of columns to 2

            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(foodItems.size) { index ->
                val foodItem = foodItems[index]
                FoodItem(food = foodItem)
            }






    }}
}

@Composable
fun FoodItem(food: Food, onFoodClick: (Food) -> Unit = {}) {
    val picture = remember { mutableStateOf(false) }
    LaunchedEffect(food.picture) {
        picture.value = true

    }
   Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 6.dp, 16.dp, 0.dp)





    ) {
        val imageModifier = Modifier
            .aspectRatio(3 / 4f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .clickable { onFoodClick(food) }

        if (picture.value) {
            Image(
                painter = rememberAsyncImagePainter(model = food.picture),
                contentDescription = "Food Image",
                modifier = imageModifier,


            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = food.name,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = LightTextColor,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = food.price,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = LightPriceColor
        )

    }


}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}