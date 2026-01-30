package com.example.randomuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.rememberLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomuser.presentation.DetailScreen
import com.example.randomuser.presentation.ListUsersScreen
import com.example.randomuser.presentation.MainScreen
import com.example.randomuser.presentation.RandomUserViewModel
import com.example.randomuser.ui.theme.RandomUserTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RandomUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RandomUserTheme {
                NavigationApp(viewModel)
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, viewModel: RandomUserViewModel) {
    val user = viewModel.user.collectAsState()
    val u = user.value.lastOrNull()
    Text(
        text = u?.toString() ?: "no results",
        modifier = modifier
    )
}

@Composable
fun NavigationApp(viewModel: RandomUserViewModel) {
    val navController = rememberNavController()

    val user = viewModel.user.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ) {
        composable("mainScreen") {
            MainScreen { gender, nat ->
                viewModel.getRandomUser(gender, nat)
                navController.navigate("listScreen") {
                    popUpTo("mainScreen") { inclusive = true } // Удаляем mainScreen из истории
                }
            }
        }
        composable("listScreen") {
            ListUsersScreen(viewModel) {
                navController.navigate("mainScreen")
            }

        }
        composable("detailScreen") {
            DetailScreen {
                navController.navigate("listScreen")
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    RandomUserTheme {
//        Greeting("Android")
//    }
//}