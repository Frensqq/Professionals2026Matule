package com.example.matule_2026.Presentation.Screen.Main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.matule_2026.Presentation.ViewModels.MainViewModel
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.uikit.UI.Black
import com.example.uikit.UI.Typography
import com.example.uikit.buttons.bigButton
import com.example.uikit.cards.cardCart
import com.example.uikit.components.SpacerH
import com.example.uikit.components.WarningWindow
import com.example.uikit.header.headerCart

@Composable
fun Cart(navController: NavHostController,viewModel: MainViewModel){

    LaunchedEffect(Unit) {
        viewModel.viewCart()
        viewModel.getProduct()

    }

    var state = viewModel.state

    val listProduct = state.listProduct
    val listCart = state.listCart

    val totalCost = remember(listProduct, listCart) {
        calculateTotalCost(listProduct,listCart)
    }

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {

        SpacerH(60)

        headerCart({

            listCart.forEach {
                index->
                viewModel.deleteCart(index.id)
            }
            viewModel.viewCart()

        }, { navController.navigate(NavigationRoutes.CATALOG)})

        SpacerH(32)



        if (listProduct.isNotEmpty()) {
            LazyColumn()
            {
                items(listCart.size) {

                    val indexCart =
                        (listProduct.mapNotNull { it.id }).indexOf(listCart[it].product_id)

                    cardCart(
                        listProduct[indexCart].title,
                        listProduct[indexCart].price,
                        listCart[it].count, { CurrentPrice ->
                            viewModel.changeCart(
                                listCart[it].id,
                                listProduct[indexCart].id, CurrentPrice
                            )
                            viewModel.viewCart()

                        },
                        { viewModel.deleteCart(state.listCart[it].id)
                            viewModel.viewCart()
                        })


                    SpacerH(32)
                }
                item {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Сумма", style = Typography().Title2_SemiBold, color = Black)

                        Text("$totalCost ₽", style = Typography().Title2_SemiBold, color = Black)
                    }

                    SpacerH(120)
                }
            }
        }
    }

    var StateButton by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 32.dp),
        contentAlignment = Alignment.BottomCenter) {

        if (StateButton) {
            StateButton = WarningWindow(StateButton, "Заказ создан", "")
        }

        bigButton("Перейти к оформлению заказа", true) {
            viewModel.viewCart()
            state.listCart.forEach {item->
                viewModel.createOrders(item.product_id, item.count)
                StateButton = true
            }
        }
    }
}
