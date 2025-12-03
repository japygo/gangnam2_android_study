package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
) {
    Column(
        modifier = modifier.size(width = 315.dp, height = 81.dp),
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