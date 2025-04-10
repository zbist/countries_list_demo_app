package com.example.myapplication.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountryListScreen(
    viewModel: CountryListViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.loadCountries()
    }

    when (val screenState = viewModel.screenState.collectAsState().value) {
        is ScreenState.Success -> ScreenContent(screenState = screenState)
        is ScreenState.Loading -> LoadingContent()
        is ScreenState.Failed -> ErrorContent(
            screenState = screenState,
            onRefreshClick = viewModel::loadCountries
        )

    }
}

@Composable
private fun ScreenContent(screenState: ScreenState.Success) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(screenState.items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(
                            R.string.name_and_region_card_value,
                            item.name.orEmpty(),
                            item.region.orEmpty()
                        ),
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.capital.orEmpty(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = item.code.orEmpty(),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Black
                    )
                )
            }
        }
    }
}

@Composable
private fun ErrorContent(
    screenState: ScreenState.Failed,
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = screenState.message,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable(onClick = onRefreshClick),
            text = stringResource(R.string.retry)
        )
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}