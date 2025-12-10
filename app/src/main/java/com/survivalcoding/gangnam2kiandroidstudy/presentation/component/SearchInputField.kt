package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SearchInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val shape = RoundedCornerShape(10.dp)
    val borderColor: Color = if (isFocused) AppColors.Primary80 else AppColors.Gray4

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color = AppColors.White, shape = shape)
            .border(1.3.dp, borderColor, shape)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(R.drawable.outline_search),
            contentDescription = "search icon",
            tint = AppColors.Gray4,
            modifier = Modifier.size(18.dp),
        )

        Box(
            modifier = Modifier.weight(1f),
        ) {
            if (value.isEmpty() && placeholder != null) {
                Text(
                    text = placeholder,
                    style = AppTextStyles.PoppinsSmallerRegular.copy(
                        color = AppColors.Gray4,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                enabled = enabled,
                textStyle = AppTextStyles.PoppinsSmallerRegular,
                singleLine = true,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                interactionSource = interactionSource,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchInputFieldPreview() {
    val (value, onValueChange) = remember { mutableStateOf("") }
    SearchInputField(
        value = value,
        onValueChange = onValueChange,
        placeholder = "Placeholder",
    )
}

@Preview(showBackground = true)
@Composable
fun LongLabelSearchInputFieldPreview() {
    val (value, onValueChange) = remember { mutableStateOf("") }
    SearchInputField(
        value = value,
        onValueChange = onValueChange,
        placeholder = "Placeholder".repeat(10),
    )
}

@Preview(showBackground = true)
@Composable
fun LongValueSearchInputFieldPreview() {
    val (value, onValueChange) = remember { mutableStateOf("Value".repeat(20)) }
    SearchInputField(
        value = value,
        onValueChange = onValueChange,
        placeholder = "Placeholder",
    )
}