package com.example.matule_2026.Presentation.Screen.Main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.matule_2026.Presentation.ViewModels.MainViewModel
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.uikit.buttons.cartButton
import com.example.uikit.cards.primaryCard
import com.example.uikit.components.SpacerH
import com.example.uikit.components.Tabbar
import com.example.uikit.components.categoryMenu
import com.example.uikit.header.headerCatalog

@Composable
fun Catalog(navController: NavHostController,viewModel: MainViewModel){

    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.getProduct()
        viewModel.viewCart()
    }

    val listProduct = state.listProduct

    var category by remember { mutableStateOf("Каталог") }
    var searchString by remember { mutableStateOf("") }

    val ListCateg: List<String> = listOf("Все","Популярные","Женщинам","Мужчинам","Детям","Аксессуары")
    var currentCategory by remember { mutableStateOf(ListCateg[0]) }

    var cost by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(horizontal = 20.dp)) {

        SpacerH(72)

        headerCatalog({CurrSearch ->
            searchString = CurrSearch}, {
            viewModel.getProduct("title ~ '$searchString'")
        }) { navController.navigate(NavigationRoutes.PROFILE)}

        SpacerH(32)

        categoryMenu(ListCateg, currentCategory,
            onClickString = { currCateg ->
                currentCategory = currCateg
                viewModel.getProduct(if(currCateg=="Все") null else "type = '$currCateg'")
                viewModel.viewCart()
            },{})

        SpacerH(20)



        LazyColumn( )
        {items(listProduct.size){

            val indexCart = (state.listCart.mapNotNull { it.product_id }).indexOf(listProduct[it].id)
            var stateBut = indexCart!=-1

            primaryCard(listProduct[it].title,
                listProduct[it].type,listProduct[it].price ,
                !stateBut,{
                    if (!stateBut) viewModel.addCart(listProduct[it].id)
                    else  viewModel.deleteCart(state.listCart[indexCart]?.id ?: "")
                    stateBut = !stateBut

                    viewModel.viewCart()
                })

            SpacerH(16)
        }
            item {

                SpacerH(42)
            }
        }



    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {


            Box(modifier = Modifier.padding(bottom = 92.dp, start = 20.dp, end = 20.dp).height(120.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center) {

                cartButton(cost, {
                    navController.navigate(NavigationRoutes.CART)
                })

            }


        Tabbar(category,
            {navController.navigate(NavigationRoutes.MAIN)},
            {navController.navigate(NavigationRoutes.CATALOG)},
            {navController.navigate(NavigationRoutes.PROJECTS)},
            {navController.navigate(NavigationRoutes.PROFILE)}
        )
    }

}

//@Preview
//@Composable
//fun PreviewCatalog(){
//
//    Catalog()
//
//}