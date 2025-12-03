package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tabs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            ) {
                BigButton(text = "Button", onClick = {})
                MediumButton(text = "Button", onClick = {})
                SmallButton(text = "Button", onClick = {})

                val (value, onValueChange) = remember { mutableStateOf("") }
                InputField(
                    label = "Label",
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = "Placeholder",
                )

                val (index, setIndex) = remember { mutableIntStateOf(0) }
                Tabs(
                    labels = listOf("First", "Second"),
                    selectedIndex = index,
                    onValueChange = setIndex,
                )
                Tabs(
                    labels = listOf("First", "Second", "Third"),
                    selectedIndex = index,
                    onValueChange = setIndex,
                )
            }
        }
    }
}