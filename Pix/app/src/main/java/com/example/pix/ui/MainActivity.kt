package com.example.pix.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pix.R
import com.example.pix.ui.theme.PixTheme
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val pictures by lazy { viewModel.pictures }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        viewModel.getPictures()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PixTheme {
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        ListView(navController)
                    }
                    composable("detail/{url}") { backStackEntry ->
                        val url = backStackEntry.arguments?.getString("url")
                        DetailPicture(url ?: "")
                    }
                }
            }
        }
    }

    @Composable
    private fun ListView(navController: NavController) {
        val pics = pictures.collectAsState().value
        Scaffold(
           content = { padding ->
               Column(Modifier.padding(padding)) {
                   LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 120.dp), contentPadding = PaddingValues(4.dp)) {
                       items(pics) { picture ->
                           Box(modifier = Modifier.width(100.dp).height(100.dp).clickable{
                               navController.navigate("detail/${encodeUrl(picture.url)}")
                           }) {
                               PictureView(picture.url)
                           }

                       }
                   }
               }
           }
        )

    }

    private fun encodeUrl(url: String): String {
        return URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    }
}

