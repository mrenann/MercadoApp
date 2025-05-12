package com.mrenann.mercadolivre.searchScreen.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.ChevronLeft
import compose.icons.evaicons.outline.Close

@Composable
fun RowScope.SearchField(
    initialQuery: String = "",
    focusRequester: FocusRequester,
    onSearch: (String) -> Unit,
    onBack: () -> Unit,
) {
    var query by rememberSaveable { mutableStateOf(initialQuery) }
    var isFocused by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = query,
        onValueChange = { query = it },
        placeholder = { Text("Buscar no Mercado Livre") },
        modifier = Modifier
            .weight(1f)
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
            },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                if (query.isNotBlank()) {
                    onSearch(query)
                }
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
        ),
        leadingIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(EvaIcons.Outline.ChevronLeft, contentDescription = "Voltar")
            }
        },
        trailingIcon = {
            if (query.isNotBlank()) {
                IconButton(onClick = { query = "" }) {
                    Icon(EvaIcons.Outline.Close, contentDescription = "Limpar texto")
                }
            }
        }
    )

    LaunchedEffect(isFocused) {
        if (isFocused) {
            focusRequester.requestFocus()
        }
    }
}