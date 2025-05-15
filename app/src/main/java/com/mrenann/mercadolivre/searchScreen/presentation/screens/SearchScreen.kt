package com.mrenann.mercadolivre.searchScreen.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.mercadolivre.searchScreen.presentation.components.RecentQueryCard
import com.mrenann.mercadolivre.searchScreen.presentation.components.SearchField
import com.mrenann.mercadolivre.searchScreen.presentation.screenModel.RecentsSearchesScreenModel

data class SearchScreen(
    val initialQuery: String = ""
) : Screen {
    @Composable
    override fun Content() {
        val focusRequester = remember { FocusRequester() }
        val navigator = LocalNavigator.currentOrThrow
        val lifecycleOwner = LocalLifecycleOwner.current
        val currentQuery = rememberSaveable { mutableStateOf(initialQuery) }
        val screenModel = koinScreenModel<RecentsSearchesScreenModel>()
        val state by screenModel.state.collectAsState()
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
                        screenModel.insertSearchQuery(query)
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

            Text(
                text = strings.searchStrings.recentSearches,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 6.dp),
                fontSize = 12.sp,
                color = Color.Gray
            )

            when (state) {
                is RecentsSearchesScreenModel.State.Loading -> {}
                is RecentsSearchesScreenModel.State.Error ->
                    Text(
                        text = strings.searchStrings.withoutRecentSearches,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                is RecentsSearchesScreenModel.State.Result -> {
                    val items = (state as RecentsSearchesScreenModel.State.Result).items
                    LazyColumn {
                        items(items) { query ->
                            RecentQueryCard(string = query, onClick = {
                                currentQuery.value = query
                                navigator.replace(
                                    ResultsSearchScreen(query)
                                )
                            })
                        }
                    }
                }
            }

        }
    }
}