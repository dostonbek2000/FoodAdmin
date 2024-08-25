package com.dostonbek.foodadmin.user

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dostonbek.foodadmin.ui.theme.LightBackgroundColor

@Composable
fun UserScreen(userViewModel: UserViewModel= viewModel()){
    val context= LocalContext.current

    val userItems by userViewModel.userItem.observeAsState(emptyList())
    val status by userViewModel.status.observeAsState("")
    LaunchedEffect(Unit) {
        userViewModel.getUser()
        
    }
    Column(modifier=Modifier.background(LightBackgroundColor)) {
        LazyVerticalGrid(columns = GridCells.Fixed(1),verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            items(userItems.size){ index->
                val userItem=userItems[index]
                UserItem(user = userItem)
                
            }



        }

    }

}
@Composable
fun UserItem(user: User,onUserClick:(User)-> Unit ={}){
    val context= LocalContext.current
    Column(modifier= Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { onUserClick(user)
        val intent=Intent(context,UserInfoActivity::class.java)
            intent.putExtra("userName",user.name)
            intent.putExtra("userSurname",user.surname)
            intent.putExtra("userAge",user.age)
            intent.putExtra("userPhoneNumber",user.phoneNumber)
            intent.putExtra("userLocation",user.location)
        context.startActivity(intent)}) {
        Text(text = user.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = user.surname, fontSize = 20.sp, fontWeight = FontWeight.Bold)

    }

}
@Preview(showBackground = true)
@Composable
fun UserPreview(){
    UserScreen()

}