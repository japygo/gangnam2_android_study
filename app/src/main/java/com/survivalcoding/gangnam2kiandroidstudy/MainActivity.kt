package com.survivalcoding.gangnam2kiandroidstudy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.DeepLinkParser
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.presentation.MyApp

class MainActivity : ComponentActivity() {
    private var deepLinkRoute by mutableStateOf<Route?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        deepLinkRoute = intent?.data?.let { DeepLinkParser.parse(it) }

        setContent {
            MyApp(deepLinkRoute = deepLinkRoute)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        deepLinkRoute = intent.data?.let { DeepLinkParser.parse(it) }
    }
}