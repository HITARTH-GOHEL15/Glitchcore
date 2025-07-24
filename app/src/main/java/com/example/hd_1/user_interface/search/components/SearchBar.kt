package com.example.hd_1.user_interface.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hd_1.ui.theme.HD_1Theme
import com.example.hd_1.ui.theme.sora_extraBold

@Composable
fun SearchBar(
   searchQuery: String,
   onSearchQueryChange: (String) -> Unit,
   modifier: Modifier = Modifier
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = MaterialTheme.colorScheme.secondary,
            backgroundColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            shape = MaterialTheme.shapes.large,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.onSecondary,
                unfocusedBorderColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "Search...",
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = sora_extraBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp , end = 12.dp)
        )
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    HD_1Theme {

    }
}