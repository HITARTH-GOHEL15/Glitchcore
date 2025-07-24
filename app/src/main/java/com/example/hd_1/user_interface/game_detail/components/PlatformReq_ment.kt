package com.example.hd_1.user_interface.game_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hd_1.ui.theme.sora_extraBold

@Composable
fun PlatformReq_ment(
    name: String?,
    recomText: String?,
    reqtext: String?
) {
    Column(
        modifier = Modifier
            .background(Color.Transparent, shape = MaterialTheme.shapes.medium)
            .border(width = 0.5.dp , color = MaterialTheme.colorScheme.secondary.copy(0.1f) , shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name?: "",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = sora_extraBold,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (reqtext != null && recomText != null) {
            Text(
                text = reqtext,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                fontFamily = sora_extraBold,
                fontWeight = FontWeight.ExtraBold
            )
        Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = recomText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                fontFamily = sora_extraBold,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun PlatformReqSection(title: String , items: List<Triple<String? , String? , String?>>) {
    if (items.isNotEmpty()) {
        Column(
            modifier = Modifier.padding(end = 8.dp , bottom = 8.dp , top = 8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                fontFamily = sora_extraBold,
                fontWeight = FontWeight.ExtraBold
            )
                Spacer(Modifier.height(8.dp))
            FlowColumn(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items.forEach { (name, recomText , reqtext) ->
                    PlatformReq_ment(
                        name ,
                        recomText ,
                        reqtext
                    )
                }
            }
        }
    }
}