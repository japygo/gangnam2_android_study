package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    hasButton: Boolean = false,
    onClick: () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val trailingIcon: @Composable (() -> Unit)? =
        if (hasButton) {
            { Spacer(modifier = Modifier.width(77.dp)) }
        } else {
            null
        }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(81.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Box(
            modifier = Modifier.height(21.dp),
        ) {
            Text(
                text = label,
                style = AppTextStyles.PoppinsSmallRegular.copy(color = AppColors.Black),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Box(
            contentAlignment = Alignment.CenterEnd,
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = AppColors.White,
                    unfocusedIndicatorColor = AppColors.Gray4,
                    focusedContainerColor = AppColors.White,
                    focusedIndicatorColor = AppColors.Primary80,
                ),
                shape = RoundedCornerShape(10.dp),
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                placeholder = {
                    placeholder?.let {
                        Text(
                            text = placeholder,
                            style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray4),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                },
                trailingIcon = trailingIcon,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
            )
            if (hasButton) {
                Box(
                    modifier = Modifier.padding(end = 9.dp),
                ) {
                    InputFieldButton(
                        text = "Send",
                        modifier = Modifier.width(59.dp),
                        onClick = onClick,
                    )
                }
            }
        }
    }
}

@Composable
fun InputFieldButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val shape = RoundedCornerShape(9.dp)
    val backgroundColor = if (isPressed || !enabled) AppColors.Gray4 else AppColors.Primary100

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(35.dp)
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick,
            )
            .background(color = backgroundColor, shape = shape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = AppTextStyles.PoppinsSmallerBold.copy(color = AppColors.White),
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 9.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    val (value, onValueChange) = remember { mutableStateOf("") }
    InputField(
        label = "Label",
        value = value,
        onValueChange = onValueChange,
        placeholder = "Placeholder",
    )
}

@Preview(showBackground = true)
@Composable
fun LongLabelInputFieldPreview() {
    val (value, onValueChange) = remember { mutableStateOf("") }
    InputField(
        label = "Label".repeat(20),
        value = value,
        onValueChange = onValueChange,
        placeholder = "Placeholder".repeat(10),
    )
}

@Preview(showBackground = true)
@Composable
fun LongValueInputFieldPreview() {
    val (value, onValueChange) = remember { mutableStateOf("Value".repeat(10)) }
    InputField(
        label = "Label",
        value = value,
        onValueChange = onValueChange,
        placeholder = "Placeholder",
    )
}

@Preview(showBackground = true)
@Composable
fun HasButtonInputFieldPreview() {
    val (value, onValueChange) = remember { mutableStateOf("Value".repeat(10)) }
    InputField(
        label = "Label",
        value = value,
        onValueChange = onValueChange,
        placeholder = "Placeholder",
        hasButton = true,
    )
}