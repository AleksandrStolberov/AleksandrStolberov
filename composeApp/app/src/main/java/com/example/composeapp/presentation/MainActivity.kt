package com.example.composeapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            View(
                { viewModel.getInfo(it) },
                {
                    startActivity(Intent(this, CacheActivity::class.java))
                })
        }

    }

    @Composable
    private fun View(
        onClick: (Int) -> Unit,
        onNextScreen: () -> Unit
    ) {

        var text: String by remember { mutableStateOf("") }
        val cardInfo = viewModel.card.collectAsState()

        Column(verticalArrangement = Arrangement.Center) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { newText -> text = newText },
                    label = { Text("Введите БИН") },
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = { onClick(text.toInt())}) {
                    Text("Поиск", color = Color.White)
                }
            }
            if (cardInfo.value.isNotEmpty()) {
                val card = cardInfo.value[0]
                CardInfo("SCHEME/NETWORK", "TYPE", card.scheme, card.type)
                CardInfo("BRAND", "PREPAID", card.brand, card.prepaid)
                CardInfo(
                    "COUNTRY",
                    "COORDINATES",
                    "${card.alpha2} ${card.country}",
                    "latitude: ${card.latitude}, longitude: ${card.latitude}"
                )
                BankInfo(card.bankName, card.bankUrl, card.bankPhone, card.bankCity)
            }
            Button(onClick = onNextScreen, modifier = Modifier.width(150.dp).align(Alignment.CenterHorizontally)) {
                Text("Cache", color = Color.White)
            }
        }
    }

    @Composable
    private fun CardInfo(type1: String, type2: String, info1: String, info2: String) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row {
                Text(text = type1, fontSize = 18.sp, modifier = Modifier.weight(1f))
                Text(text = type2, fontSize = 18.sp, modifier = Modifier.weight(1f))
            }
            Row {
                Text(info1, modifier = Modifier.weight(1f))
                Text(info2, modifier = Modifier.weight(1f))
            }
        }
    }

    @Composable
    private fun BankInfo(bankName: String, bankUrl: String, bankPhone: String, bankCity: String) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("BANK", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
            Text(text = "$bankName $bankCity", fontSize = 16.sp, modifier = Modifier.padding(4.dp))
            Text(text = bankPhone, fontSize = 16.sp, modifier = Modifier.padding(4.dp))
            Text(text = bankUrl, fontSize = 16.sp, modifier = Modifier.padding(4.dp))
        }
    }

}