package com.mrenann.mercadolivre.homeScreen.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Search

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = modifier
            .height(48.dp)
            .padding(0.dp),
        value = query,
        maxLines = 1,
        onValueChange = onQueryChange,
        singleLine = true,
        placeholder = {
            Text(
                fontSize = 14.sp,
                lineHeight = 14.sp,
                text = "Pesquisar no mercado livre"
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            lineHeight = 14.sp
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable {
                        if (query.length > 3) onSearch(query)
                    },
                imageVector = EvaIcons.Outline.Search,
                contentDescription = "Search Icon"
            )
        },
        shape = CircleShape,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (query.length > 3) onSearch(query)
                keyboardController?.hide()
            }
        ),
    )
}

@Preview
@Composable
fun SearchComponentPreview() {
    SearchBar(query = "", onQueryChange = {}, onSearch = {})
}