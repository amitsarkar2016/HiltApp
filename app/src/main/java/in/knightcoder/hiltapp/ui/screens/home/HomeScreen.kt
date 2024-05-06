package `in`.knightcoder.hiltapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun HomeScreen() {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val homeResponse by viewModel.homeResponse.collectAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchHomeData()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (homeResponse != null) {
            if (homeResponse?.data == null) {
                Text(
                    text = "Error: something went wrong",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            } else {
                Text(
                    text = homeResponse?.message ?: "No Data Found",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(48.dp)
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}