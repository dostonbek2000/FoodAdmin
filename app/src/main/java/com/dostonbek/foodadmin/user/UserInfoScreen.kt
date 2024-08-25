package com.dostonbek.foodadmin.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dostonbek.foodadmin.ui.theme.LightTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoScreen() {
    val context= LocalContext.current
    val activity = context as UserInfoActivity
    val userName = activity.intent.getStringExtra("userName") ?: ""
    val userSurname = activity.intent.getStringExtra("userSurname") ?: ""
    val userAge = activity.intent.getStringExtra("userAge") ?: ""
    val userPhoneNumber = activity.intent.getStringExtra("userPhoneNumber") ?: ""
    val userLocation = activity.intent.getStringExtra("userLocation") ?: ""
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "User Information") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = LightTextColor
            )
        )
    }) { innerPadding ->
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = userName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightTextColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = userSurname,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightTextColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = userAge,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightTextColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = userPhoneNumber,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightTextColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = userLocation,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = LightTextColor
                )


            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoPreview() {

}