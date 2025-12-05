package com.survivalcoding.gangnam2kiandroidstudy.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = AppColors.White)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 30.dp),
    ) {
        val (email, setEmail) = rememberSaveable { mutableStateOf("") }
        val (password, setPassword) = rememberSaveable { mutableStateOf("") }

        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier.padding(top = 50.dp, bottom = 57.dp),
        ) {
            Text(
                text = "Hello,",
                style = AppTextStyles.PoppinsHeaderBold,
            )
            Text(
                text = "Welcome Back!",
                style = AppTextStyles.PoppinsLargeRegular,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            InputField(
                label = "Email",
                value = email,
                onValueChange = setEmail,
                placeholder = "Enter Email",
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    },
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
            )
            InputField(
                label = "Enter Password",
                value = password,
                onValueChange = setPassword,
                placeholder = "Enter Password",
                visualTransformation = PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    },
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
            )
        }

        Text(
            text = "Forgot Password?",
            style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Secondary100),
            modifier = Modifier
                .padding(top = 20.dp, bottom = 25.dp)
                .padding(horizontal = 10.dp)
                .clickable {},
        )

        BigButton(text = "Sign In") {}

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = AppColors.Gray4,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "Or Sign in With",
                style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray4),
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = AppColors.Gray4,
                modifier = Modifier.weight(1f),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 101.dp),
            horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(10.dp),
                        ambientColor = Color(0xFF696969),
                        spotColor = Color(0xFF696969),
                    )
                    .background(
                        color = AppColors.White,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .clickable {}
                    .padding(10.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = "google",
                    modifier = Modifier.size(24.dp),
                )
            }

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(10.dp),
                        ambientColor = Color(0xFF696969),
                        spotColor = Color(0xFF696969),
                    )
                    .background(
                        color = AppColors.White,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .clickable {}
                    .padding(10.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.facebook),
                    contentDescription = "facebook",
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
        ) {
            Text(
                text = "Don’t have an account?",
                style = AppTextStyles.PoppinsSmallerRegular.copy(
                    color = AppColors.Black,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
            Text(
                text = "Sign up",
                style = AppTextStyles.PoppinsSmallerRegular.copy(
                    color = AppColors.Secondary100,
                    fontWeight = FontWeight.SemiBold,
                ),
                modifier = Modifier.clickable {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen()
}