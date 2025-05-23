package com.mrenann.mercadolivre.searchScreen.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.lyricist.strings
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
    val strings = strings.searchStrings

    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(
                text = initialQuery,
                selection = TextRange(initialQuery.length)
            )
        )
    }
    var isFocused by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onQueryChanged(it.text)
        },
        placeholder = { Text(strings.searchOnMercadoLivre) },
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
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        leadingIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(EvaIcons.Outline.ChevronLeft, contentDescription = strings.backIconDescription)
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
                    Icon(EvaIcons.Outline.Close, contentDescription = strings.clearIconDescription)
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