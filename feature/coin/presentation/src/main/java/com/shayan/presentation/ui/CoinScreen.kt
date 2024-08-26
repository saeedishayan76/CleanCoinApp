package com.shayan.presentation.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shayan.presentation.ui.contract.CoinContract

@Composable
fun CoinScreenRoot(
    viewModel: CoinViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.initialize()
        viewModel.fetchChart("bitcoin", "BTC")

        viewModel.effect.collect(){
            Log.d("TAG", "Show ERROR: $it")
            when(it){
                is CoinContract.CoinEffect.ShowError -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    val state = viewModel.viewState.collectAsState().value
    CoinScreen(state = state){ id, symbol ->
        viewModel.setEvent(CoinContract.CoinIntent.CoinClicked(id, symbol))
    }
}

@Composable
fun CoinScreen(
    state: CoinContract.CoinUiState,
    modifier: Modifier = Modifier,
    onCoinClick: (String, String) -> Unit
) {
    Scaffold(
        containerColor = Color(0xff0A0F1B),
        topBar = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color(0xff0A0F1B))
                    .padding(16.dp)
            ) {
                if (state.isLoading){
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Text(
                    text = "Coins", fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 29.sp, color = Color(0xffAEB2BA)
                )
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 26.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .width(30.dp)
                        .height(4.dp)
                        .background(color = Color(0xffAEB2BA))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {

                    Text(
                        text = state.chartSymbol, fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp, color = Color(0xffAEB2BA)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    ChartScreen(state.chartData)
                }
            }
        }
    ) {

        LazyColumn(
            contentPadding = it
        ) {
            itemsIndexed(state.coins) { index, asset ->
                CoinItem(coin = asset){ id, symbol ->
                    onCoinClick(id, symbol)
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShowPreviewContent() {
    CoinScreen(state = CoinContract.CoinUiState()){ _, _ ->}
}
