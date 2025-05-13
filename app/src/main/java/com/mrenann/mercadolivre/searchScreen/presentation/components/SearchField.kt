package com.mrenann.mercadolivre.searchScreen.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.ChevronLeft
import compose.icons.evaicons.outline.Close

@Composable
fun RowScope.SearchField(
    initialQuery: String = "",
    focusRequester: FocusRequester,
    onSearch: (String) -> Unit,
    onQueryChanged: (String) -> Unit,
    onBack: () -> Unit,
) {
    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(
                text = initialQuery,
                selection = TextRange(initialQuery.length)
            )
        )
    }
    var isFocused by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onQueryChanged(it.text)
        },
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
                if (textFieldValue.text.isNotBlank()) {
                    onSearch(textFieldValue.text)
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
            cursorColor = Color.Black,
            selectionColors = TextSelectionColors(
                handleColor = Color.Blue,
                backgroundColor = Color.LightGray.copy(alpha = 0.4f)
            )
        ),
        leadingIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(EvaIcons.Outline.ChevronLeft, contentDescription = "Voltar")
            }
        },
        trailingIcon = {
            if (textFieldValue.text.isNotBlank()) {
                IconButton(onClick = {
                    textFieldValue = TextFieldValue(
                        text = "",
                        selection = TextRange(0)
                    )
                }) {
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

    LaunchedEffect(initialQuery) {
        if (initialQuery != textFieldValue.text) {
            textFieldValue = TextFieldValue(
                text = initialQuery,
                selection = TextRange(initialQuery.length)
            )
        }
    }
}