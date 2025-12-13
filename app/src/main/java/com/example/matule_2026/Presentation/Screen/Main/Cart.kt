package com.example.matule_2026.Presentation.Screen.Main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.UI.Black
import com.example.uikit.UI.Typography
import com.example.uikit.buttons.bigButton
import com.example.uikit.buttons.cartButton
import com.example.uikit.cards.primaryCard
import com.example.uikit.components.SpacerH
import com.example.uikit.components.Tabbar
import com.example.uikit.header.headerCart

@Composable
fun Cart(){

    var Cost = 2490

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {

        SpacerH(60)

        headerCart({}) { }

        SpacerH(32)

        LazyColumn()
        {items(2){



            SpacerH(32)
        }
            item {

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Сумма", style = Typography().Title2_SemiBold, color = Black)

                    Text("$Cost ₽", style = Typography().Title2_SemiBold, color = Black)
                }

                SpacerH(120)
            }
        }

    }

    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 32.dp),
        contentAlignment = Alignment.BottomCenter) {


        bigButton("Перейти к оформлению заказа", true) { }

    }
}

@Preview
@Composable
fun PreviewCart(){

    Cart()

}