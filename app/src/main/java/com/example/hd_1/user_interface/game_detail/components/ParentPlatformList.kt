package com.example.hd_1.user_interface.game_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hd_1.data.rawgModels.ParentPlatformModel
import com.example.hd_1.ui.theme.sora_bold
import com.example.hd_1.ui.theme.sora_extraBold

@Composable
fun PlatformsSection(platforms: List<ParentPlatformModel>) {
    if(platforms.isNotEmpty()) {
        Column() {
            Text(
                text = "Available for",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                fontFamily = sora_extraBold,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                platforms.forEach { parent ->
                    val name = parent.platform?.name ?: return@forEach
                    PlatformChip(name)
                }
            }
        }
    }
}

@Composable
fun PlatformChip(name: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
    ) {
        Text(
            text = name,
            fontFamily = sora_bold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = 12.dp , vertical = 6.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}