package com.example.randomuser.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomuser.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToList: (gender: String, nat: String) -> Unit
) {

    val nationality = Nationality.entries.map { it.countryName }
    var selectedNat by remember { mutableStateOf(nationality.first()) }
    var selectedGender by remember { mutableStateOf("Male") }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                        Text("Generate user", fontSize = 18.sp, color = Color.Blue)
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp)
        ) {

            Text(
                "Select Gender:", fontSize = 18.sp, color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            DropDown(
                selectedValue = selectedGender,
                onValueChange = { selectedGender = it },
                options = listOf("Male", "Female")
            )

            Text(
                "Select Nationality:", fontSize = 18.sp, color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            DropDown(
                selectedValue = selectedNat,
                onValueChange = { selectedNat = it },
                options = nationality
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    onNavigateToList(selectedGender, selectedNat)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                )
            ) {
                Text("Generate")
            }

        }
    }

}

@Composable
fun DropDown(
    selectedValue: String,
    onValueChange: (String) -> Unit,
    options: List<String>,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)

    ) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(selectedValue)
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.arrow_drop_down),
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(option)
                    },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen { _, _ -> }
}