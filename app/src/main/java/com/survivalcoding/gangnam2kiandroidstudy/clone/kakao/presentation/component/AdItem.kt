package com.survivalcoding.gangnam2kiandroidstudy.clone.kakao.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.clone.kakao.data.model.Ad
import com.survivalcoding.gangnam2kiandroidstudy.core.util.orPreview
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun AdItem(
    ad: Ad,
    modifier: Modifier = Modifier,
    onClick: (Ad) -> Unit = {},
) {
    val shape = RoundedCornerShape(10.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(shape)
            .clickable {
                onClick(ad)
            }
            .background(color = AppColors.White, shape = shape)
            .padding(horizontal = 30.dp, vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = ad.title,
                    style = AppTextStyles.PoppinsLargeBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = ad.description,
                    style = AppTextStyles.PoppinsSmallRegular.copy(AppColors.Gray2),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            AsyncImage(
                model = ad.imageUrl.orPreview,
                contentDescription = "광고",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp)),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddItemPreview() {
    AdItem(
        ad = Ad(
            title = "광고 제목",
            description = "광고 설명",
            imageUrl = "",
            linkUrl = "",
        ),
    )
}