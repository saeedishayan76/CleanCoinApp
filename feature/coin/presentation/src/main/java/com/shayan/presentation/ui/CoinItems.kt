package com.shayan.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shayan.cleanWithUnit.R
import com.shayan.presentation.uiModel.CoinUiModel


@Composable
fun CoinItem(
    coin: CoinUiModel,
    onCoinClick : (String, String) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onCoinClick(coin.id, coin.symbol)
            }
            .fillMaxWidth()
            .background(Color(0xff0A0F1B))
            .padding(16.dp)
    ) {

        Row(modifier = Modifier.weight(1f)) {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                model = coin.image,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(
                    fontFamily = FontFamily.Serif,
                    text = coin.symbol, fontWeight = FontWeight.Bold, color = Color(0xffAEB2BA),
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Normal,
                    text = coin.name, color = Color(0xffAEB2BA),
                    fontSize = 10.sp
                )
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                text ="${coin.price} $",
                color = Color(0xffAEB2BA),
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            if (!coin.isPriceChangeNegative) {
                Icon(
                    painter = painterResource(id = com.shayan.cleanWithUnit.R.drawable.b),
                    contentDescription = null,
                    tint = Color.Green
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.a),
                    contentDescription = null,
                    tint = Color.Red
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${coin.priceChange}%",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xffAEB2BA),
                fontSize = 11.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowCoinPreviewContent() {
    CoinItem(coin = CoinUiModel("btc",
        "Bitcoin","","","64249.00","-0.23%", true)){ _, _ ->}
}