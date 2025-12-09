package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.splash_background),
        contentDescription = "splash background",
        modifier = Modifier.fillMaxSize(),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x40000000),
                        AppColors.Black,
                    ),
                ),
            ),
    )

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 104.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.splash_icon),
                contentDescription = "splash icon",
                tint = AppColors.White,
                modifier = Modifier.size(79.dp)
            )
            Text(
                text = "100K+ Premium Recipe",
                style = AppTextStyles.PoppinsMediumBold.copy(color = AppColors.White),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 222.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text(
                text = "Get\nCooking",
                style = AppTextStyles.PoppinsTitleBold.copy(color = AppColors.White),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Simple way to find Tasty Recipe",
                style = AppTextStyles.PoppinsNormalRegular.copy(color = AppColors.White),
            )
        }

        MediumButton(
            text = "Start Cooking",
            modifier = Modifier
                .padding(top = 64.dp)
                .padding(horizontal = 66.dp),
        ) {}
    }
}

@Preview(showBackground = false)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}