package com.dostonbek.foodadmin.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.dostonbek.foodadmin.navigation.NavigationBar
import com.dostonbek.foodadmin.user.FetchDataService
import com.dostonbek.foodadmin.user.UserViewModel
import com.dostonbek.foodadmin.user.UserViewModel.requestSMSPermission
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavigationBar()
        }

        requestSMSPermission(this)

        // Start the foreground service
        val serviceIntent = Intent(this, FetchDataService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)

    }


}
