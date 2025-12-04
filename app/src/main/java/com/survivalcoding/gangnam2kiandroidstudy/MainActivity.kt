package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RatingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RatingDialog
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton

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
                val ingredient = Ingredient(
                    name = "Tomatos",
                    imageUrl = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
                    amount = "500g",
                )
                IngredientItem(ingredient = ingredient)

                val recipe = Recipe(
                    name = "spice roasted chicken with flavored rice",
                    imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                    chef = "Chef John",
                    time = 20,
                    rating = 4.0,
                )
                RecipeCard(recipe = recipe)

                val select = remember { mutableStateOf(false) }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    RatingButton(
                        text = "5",
                        isSelected = select.value,
                        onClick = { select.value = !select.value },
                    )
                    RatingButton(text = "5", isSelected = true, onClick = {})
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    FilterButton(
                        text = "Text",
                        isSelected = select.value,
                        onClick = { select.value = !select.value },
                    )
                    FilterButton(text = "Text", isSelected = true) {}
                }

                BigButton(text = "Button", enabled = false, onClick = {})
                MediumButton(text = "Button", enabled = false, onClick = {})
                SmallButton(text = "Button", enabled = false, onClick = {})

                Column {
                    val score = remember { mutableIntStateOf(0) }
                    Text(text = "${score.intValue} 점")
                    val showDialog = remember { mutableStateOf(false) }
                    SmallButton(text = "평점", onClick = { showDialog.value = true })
                    if (showDialog.value) {
                        RatingDialog(
                            title = "Rate recipe",
                            actionName = "Send",
                            onDismissRequest = {
                                score.intValue = 0
                                showDialog.value = false
                            },
                        ) {
                            score.intValue = it
                            showDialog.value = false
                        }
                    }
                }
            }
        }
    }
}