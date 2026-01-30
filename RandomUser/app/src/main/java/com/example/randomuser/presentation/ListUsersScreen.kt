package com.example.randomuser.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.Dob
import com.example.domain.Location
import com.example.domain.Name
import com.example.domain.Picture
import com.example.domain.Street
import com.example.domain.User
import com.example.randomuser.R

@Composable
fun ListUsersScreen(
    viewModel: RandomUserViewModel,
    onNavigateToMain: () -> Unit
) {

    val state = viewModel.state.collectAsState()
    val user = viewModel.user.collectAsState()
    when (state.value) {
        is State.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is State.Success -> {
            Scaffold(
                topBar = {
                   
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            onNavigateToMain()
                        }
                    ) {
                        Text("+")
                    }
                }
            ) { innerPadding ->
                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    items(user.value) {
                        UserItem(it) {
                            viewModel.deleteUser(it)
                        }
                    }
                }
            }
        }

        is State.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text((state.value as State.Error).message)
            }
        }
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserItem(user: User, onDelete: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(140.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(12.dp),
                clip = true,
                ambientColor = Color.Black.copy(alpha = 0.4f),
                spotColor = Color.Black.copy(alpha = 0.1f)
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.CenterStart

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            GlideImage(
                model = user.picture.medium,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(4.dp).
                fillMaxHeight()
            ) {
                Text(
                    user.name.first + ' ' + user.name.last,
                    color = Color.Blue,
                    fontStyle = FontStyle.Italic
                )
                Text(user.phone)

                Row(verticalAlignment = Alignment.CenterVertically) {

                    val nationality = Nationality.entries.find { it.name == user.nat }
                    Image(
                        painter = painterResource(nationality?.imageResId ?: R.drawable.circle_grey),
                        contentDescription = null,
                        modifier = Modifier
                            .size(13.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(Modifier.size(2.dp))
                    Text(user.nat, fontSize = 13.sp)

                }


            }

        }

        Button(
            modifier = Modifier.size(16.dp).align(Alignment.TopEnd).offset(y = 12.dp),
            onClick = {
                onDelete()
            }
        ) {
            Text("x")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun UserView() {
    val users = listOf(
        TestUser.user,TestUser.user,
        TestUser.user,TestUser.user
    )
    LazyColumn(modifier = Modifier) {
        items(users) {
            UserItem(it) {

            }
        }
    }
}

object TestUser {
    val user = User(
        gender = "female",
        name = Name(
            first = "Jennie",
            last = "Nichols"
        ),
        location = Location(
            street = Street(
                number = "8929",
                name = "Valwood Pkwy"
            ),
            city = "Billings",
            state = "Michigan",
            country = "United States"
        ),
        email = "jennie.nichols@example.com",
        dateOfBirth = Dob(
            date = "1992-03-08T15:13:16.688Z",
            age = "30"
        ),
        phone = "(272) 790-0888",
        picture = Picture(
            large = "https://randomuser.me/api/portraits/men/75.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
        ),
        nat = "US"
    )
}
