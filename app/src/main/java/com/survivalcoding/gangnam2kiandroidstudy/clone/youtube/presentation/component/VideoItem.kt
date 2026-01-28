package com.survivalcoding.gangnam2kiandroidstudy.clone.youtube.presentation.component

import android.icu.number.Notation
import android.icu.number.NumberFormatter
import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
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
import com.survivalcoding.gangnam2kiandroidstudy.clone.youtube.data.model.Video
import com.survivalcoding.gangnam2kiandroidstudy.core.util.orPreview
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun VideoItem(
    video: Video,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            AsyncImage(
                model = video.thumbnailUrl.orPreview,
                contentDescription = "thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .background(
                        color = AppColors.Black.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(4.dp),
                    )
                    .padding(horizontal = 4.dp, vertical = 2.dp),
            ) {
                Text(
                    text = video.duration,
                    style = AppTextStyles.PoppinsSmallerRegular.copy(AppColors.White),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            AsyncImage(
                model = video.channelImageUrl.orPreview,
                contentDescription = "channel image",
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(18.dp)),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = video.title,
                    style = AppTextStyles.PoppinsNormalBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Text(
                        text = video.channelName,
                        style = AppTextStyles.PoppinsSmallerRegular.copy(AppColors.Gray3),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = "more",
                        modifier = Modifier.size(16.dp),
                        tint = AppColors.Gray3,
                    )
                    Text(
                        text = "조회수 ${formatCompactNumber(video.viewCount, Locale.KOREA)}회",
                        style = AppTextStyles.PoppinsSmallerRegular.copy(AppColors.Gray3),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = "more",
                        modifier = Modifier.size(16.dp),
                        tint = AppColors.Gray3,
                    )
                    Text(
                        text = getRelativeTime(stringToMillis(video.publishedAt)),
                        style = AppTextStyles.PoppinsSmallerRegular.copy(AppColors.Gray3),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = "more",
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

private fun formatCompactNumber(number: Long, locale: Locale): String {
    return NumberFormatter.withLocale(locale)
        .notation(Notation.compactShort())
        .format(number)
        .toString()
}

private fun getRelativeTime(pastTimeMillis: Long): String {
    return DateUtils.getRelativeTimeSpanString(
        pastTimeMillis,
        System.currentTimeMillis(),
        DateUtils.MINUTE_IN_MILLIS,
        DateUtils.FORMAT_ABBREV_RELATIVE,
    ).toString()
}

private fun stringToMillis(
    dateString: String,
    pattern: String = "yyyy-MM-dd HH:mm:ss",
    zoneId: ZoneId = ZoneId.systemDefault(),
): Long {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val localDateTime = LocalDateTime.parse(dateString, formatter)
    return localDateTimeToMillis(localDateTime, zoneId)
}

private fun localDateTimeToMillis(
    localDateTime: LocalDateTime,
    zoneId: ZoneId = ZoneId.systemDefault(),
): Long {
    val zonedDateTime = localDateTime.atZone(zoneId)
    return zonedDateTime.toInstant().toEpochMilli()
}

@Preview(showBackground = true)
@Composable
fun VideoItemPreview() {
    val video = Video(
        id = 1L,
        title = "스파6 - 세번 잡히면 죽습니다.",
        thumbnailUrl = "thumbnailUrl",
        channelName = "아빠킹",
        channelImageUrl = "channelImageUrl",
        viewCount = 40000L,
        publishedAt = "2025-12-08 12:22:22",
        duration = "16:21",
    )

    VideoItem(video = video)
}
