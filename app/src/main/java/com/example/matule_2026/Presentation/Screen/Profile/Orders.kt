package com.example.matule_2026.Presentation.Screen.Profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.matule_2026.Presentation.ViewModels.MainViewModel
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.uikit.R
import com.example.uikit.UI.Black
import com.example.uikit.UI.Description
import com.example.uikit.UI.Typography
import com.example.uikit.bubble.buttonBack
import com.example.uikit.cards.backgroundCards
import com.example.uikit.components.SpacerH
import com.example.uikit.components.SpacerW

@Composable
fun Orders(viewModel: MainViewModel, navController: NavController){

    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.getOrders()
        viewModel.getProduct()
    }

    val listProduct = state.listProduct
    val listOrder = state.listOrders


    Column(modifier = Modifier.padding(horizontal = 20.dp)) {

        SpacerH(72)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            buttonBack { navController.navigate(NavigationRoutes.PROFILE) }

            Text("Заказы")

            SpacerW(30)

        }

        SpacerH(32)



        LazyColumn( )
        {items(listOrder.size){

            val indexCart = (state.listProduct.mapNotNull { it.id }).indexOf(listOrder[it].product_id)
            var stateBut = indexCart!=-1


            if (stateBut) {
                Box() {

                    backgroundCards()

                    Column(
                        modifier = Modifier.fillMaxWidth().height(138.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    )
                    {

                        Row(
                            Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                listProduct[indexCart].title, style = Typography().Headline_Medium,
                                maxLines = 2, color = Black, modifier = Modifier.fillMaxWidth(0.9f)
                            )


                            Icon(
                                painter = painterResource(R.drawable.close),
                                contentDescription = null,
                                Modifier.size(20.dp).clickable {

                                },
                                tint = Description,

                                )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 20.dp, bottom = 20.dp, end = 9.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Text(
                                "${listProduct[indexCart].price} ₽",
                                style = Typography().Title3_Medium,
                                color = Black
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Text(
                                    "${listOrder[it].count} штук",
                                    style = Typography().Text_Regular,
                                    color = Black
                                )

                            }

                        }

                    }
                }
            }


            SpacerH(16)
        }
            item {

                SpacerH(42)
            }
        }
    }
}