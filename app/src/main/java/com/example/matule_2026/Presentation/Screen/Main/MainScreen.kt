package com.example.matule_2026.Presentation.Screen.Main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.matule_2026.Presentation.ViewModels.MainViewModel
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.uikit.UI.Accent
import com.example.uikit.UI.Placeholders
import com.example.uikit.UI.Typography
import com.example.uikit.cards.primaryCard
import com.example.uikit.components.SpacerH
import com.example.uikit.components.SpacerW
import com.example.uikit.components.Tabbar
import com.example.uikit.components.categoryMenu
import com.example.uikit.search.searchField

@Composable
fun MainScreen(navController: NavHostController,viewModel: MainViewModel){

    var category by remember { mutableStateOf("Главная") }
    var searchString by remember { mutableStateOf("") }



    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.getNews()
        viewModel.getProduct()
        viewModel.viewCart()
    }

    val listNews = state.listNews
    val ListProduct = state.listProduct

    val ListCateg: List<String> = listOf("Все","Популярные","Женщинам","Мужчинам","Детям","Аксессуары")
    var currentCategory by remember { mutableStateOf(ListCateg[0]) }

    Column(modifier = Modifier.fillMaxSize().padding(start = 20.dp)) {

        SpacerH(68)

        Box(modifier = Modifier.padding(end = 20.dp)) {
            searchField({currSearch ->
                searchString = currSearch}) {
                viewModel.getProduct("title ~ '$searchString'")
            }
        }

        SpacerH(32)

        Text("Акции и новости", style = Typography().Title3_Semibold,
            color = Placeholders)

        SpacerH(16)

        LazyRow() {
            items(listNews.size){
                Box(modifier = Modifier.width(270.dp).height(152.dp)
                    .clip(RoundedCornerShape(12.dp)).background(Accent))
                {

                    if (listNews[it].collectionId.isNotEmpty()) {
                        AsyncImage(

                            model = ImageRequest.Builder(LocalContext.current)
                                .data(
                                    viewModel.getImage(
                                        listNews[it].collectionId,
                                        listNews[it].id,
                                        listNews[it].newsImage
                                    )
                                )
                                .size(Size.ORIGINAL)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                                .background(Color.White),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }

                SpacerW(16)
            }
        }

        SpacerH(32)

        Text("Каталог описаний", style = Typography().Title3_Semibold,
            color = Placeholders)

        SpacerH(15)


        categoryMenu(ListCateg, currentCategory,
            onClickString = { currCateg ->
                currentCategory = currCateg
                viewModel.getProduct(if(currCateg=="Все") null else "type = '$currCateg'")
            },
            {}
            )

        SpacerH(25)



        LazyColumn(modifier = Modifier.padding(end = 20.dp))
        {items(ListProduct.size){

            val indexCart = (state.listCart.mapNotNull { it.product_id }).indexOf(ListProduct[it].id)
            var stateBut = indexCart!=-1

            primaryCard(ListProduct[it].title,
                ListProduct[it].type,ListProduct[it].price ,
                !stateBut,{
                    if (!stateBut) viewModel.addCart(ListProduct[it].id)
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
//fun PreviewMainScreen(){
//    MainScreen()
//}