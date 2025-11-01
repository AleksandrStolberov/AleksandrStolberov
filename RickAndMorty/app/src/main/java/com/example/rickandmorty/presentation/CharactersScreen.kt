package com.example.rickandmorty.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.domain.Character

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    viewModel: HomeViewModel
) {
    val characters = viewModel.items.collectAsLazyPagingItems()
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchResult = viewModel.searched.collectAsState()

    Scaffold(
        topBar = {
            SearchBar(
                query = text,
                onQueryChange = { text = it },
                onSearch = {
                    active = false
                    viewModel.performSearch(text)
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text("Search characters...") }
            ) {}
        },
        content = {
            Text(text = searchResult.toString())
        },
        modifier = Modifier.padding(16.dp)
    )

}

@Composable
fun CharacterCard(item: Character) {

}

//                LazyColumn(modifier = Modifier.padding(16.dp)) {
//                    items(searchResult.value) { item ->
//                        Text(text = item.name)
//                    }
//
//                }
