package com.example.composeapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.coroutineScope
import com.example.composeapp.data.model.Card
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CacheActivity : AppCompatActivity() {

    private val viewModel: CacheViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.coroutineScope.launch {
            viewModel.getAllCards().collect {
                setContent {
                    ListView(it)
                }
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ListView(cards: List<Card>) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                    }

                )
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                LazyColumn {
                    items(cards.size) {
                        Text(
                            cards[it].toString(),
                            modifier = Modifier.padding(8.dp),
                            fontSize = 12.sp
                        )
                    }
                }
                Button(
                    onClick = {
                        lifecycle.coroutineScope.launch {
                            viewModel.deleteAllCards()
                        }
                    }, modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                {
                    Text("Clear", color = Color.White)
                }
            }
        }
    }

}