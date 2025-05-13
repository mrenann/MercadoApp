package com.mrenann.mercadolivre.searchScreen.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.mercadolivre.searchScreen.presentation.components.SearchField

data class SearchScreen(
    val initialQuery: String = ""
) : Screen {
    @Composable
    override fun Content() {
        val focusRequester = remember { FocusRequester() }
        val navigator = LocalNavigator.currentOrThrow
        val lifecycleOwner = LocalLifecycleOwner.current
        val currentQuery = rememberSaveable { mutableStateOf(initialQuery) }

        LaunchedEffect(lifecycleOwner) {
            focusRequester.requestFocus()
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                SearchField(
                    initialQuery = currentQuery.value,
                    focusRequester = focusRequester,
                    onSearch = { query ->
                        currentQuery.value = query
                        navigator.replace(
                            ResultsSearchScreen(query)
                        )
                    },
                    onQueryChanged = { query ->
                        currentQuery.value = query
                    },
                    onBack = { navigator.pop() }
                )
            }

            Spacer(Modifier.height(16.dp))
            Text(
                text = "Últimas buscas",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
    }
}