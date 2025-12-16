@file:OptIn(ExperimentalMaterial3Api::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RateFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchFilter
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.TimeFilterType
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import kotlinx.coroutines.launch

@Composable
fun FilterSearchBottomSheet(
    modifier: Modifier = Modifier,
    isSheetVisible: Boolean = false,
    searchFilter: RecipeSearchFilter = RecipeSearchFilter(),
    onDismissRequest: () -> Unit = {},
    onFilterChange: (RecipeSearchFilter) -> Unit = { _ -> },
    onFilter: () -> Unit = {},
) {
    if (!isSheetVisible) {
        return
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = AppColors.White,
        shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp),
    ) {
        Column(
            modifier = modifier.padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Text(
                text = "Filter Search",
                style = AppTextStyles.PoppinsSmallBold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            TimeFilterSection(searchFilter, onFilterChange)

            RateFilterSection(searchFilter, onFilterChange)

            CategoryFilterSection(searchFilter, onFilterChange)

            SmallButton(
                text = "Filter",
                modifier = Modifier
                    .padding(horizontal = 100.dp)
                    .padding(top = 10.dp, bottom = 22.dp),
            ) {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onFilter()
                    }
                }
            }
        }
    }
}

@Composable
private fun TimeFilterSection(
    searchFilter: RecipeSearchFilter = RecipeSearchFilter(),
    onFilterChange: (RecipeSearchFilter) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = "Time",
            style = AppTextStyles.PoppinsSmallBold,
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            TimeFilterType.entries.forEach {
                FilterButton(
                    text = it.label,
                    isSelected = it == searchFilter.time,
                ) {
                    val selectedTime = it.takeUnless { it == searchFilter.time }
                    onFilterChange(searchFilter.copy(time = selectedTime))
                }
            }
        }
    }
}

@Composable
private fun RateFilterSection(
    searchFilter: RecipeSearchFilter = RecipeSearchFilter(),
    onFilterChange: (RecipeSearchFilter) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = "Rate",
            style = AppTextStyles.PoppinsSmallBold,
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            RateFilterType.entries.forEach {
                RatingButton(
                    text = it.label,
                    isSelected = it == searchFilter.rate,
                ) {
                    val selectedRate = it.takeUnless { it == searchFilter.rate }
                    onFilterChange(searchFilter.copy(rate = selectedRate))
                }
            }
        }
    }
}

@Composable
private fun CategoryFilterSection(
    searchFilter: RecipeSearchFilter = RecipeSearchFilter(),
    onFilterChange: (RecipeSearchFilter) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = "Category",
            style = AppTextStyles.PoppinsSmallBold,
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            CategoryFilterType.entries.forEach {
                FilterButton(
                    text = it.label,
                    isSelected = it == searchFilter.category,
                ) {
                    val selectedCategory = it.takeUnless { it == searchFilter.category }
                    onFilterChange(searchFilter.copy(category = selectedCategory))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterSearchBottomSheetPreview() {
    FilterSearchBottomSheet(
        isSheetVisible = true,
    )
}