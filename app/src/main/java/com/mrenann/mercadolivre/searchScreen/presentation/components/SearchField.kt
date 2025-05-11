package com.mrenann.mercadolivre.searchScreen.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import cafe.adriel.voyager.navigator.Navigator
import com.mrenann.mercadolivre.searchScreen.presentation.screens.ResultsSearchScreen

@Composable
fun RowScope.SearchField(
    query: String,
    focusRequester: FocusRequester,
    navigator: Navigator
) {
    var query1 = query
    TextField(
        value = query1,
        onValueChange = { query1 = it },
        placeholder = { Text("Buscar...") },
        modifier =
            Modifier
                .weight(1f)
                .focusRequester(focusRequester),
        singleLine = true,
        keyboardActions =
            KeyboardActions(onSearch = {
                if (query1.isNotBlank()) {
                    navigator.replace(
                        ResultsSearchScreen(
                            query1,
                        ),
                    )
                }
            }),
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
        leadingIcon = {
            IconButton(onClick = { navigator.pop() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
            }
        },
        trailingIcon = {
            if (query1.isNotBlank()) {
                IconButton(onClick = { query1 = "" }) {
                    Icon(Icons.Default.Close, contentDescription = "Limpar texto")
                }
            }
        },
    )
}