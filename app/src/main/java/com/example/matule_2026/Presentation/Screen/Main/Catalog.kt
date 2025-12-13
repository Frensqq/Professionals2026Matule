package com.example.matule_2026.Presentation.Screen.Main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.buttons.cartButton
import com.example.uikit.cards.primaryCard
import com.example.uikit.components.SpacerH
import com.example.uikit.components.Tabbar
import com.example.uikit.components.categoryMenu
import com.example.uikit.header.headerCatalog

@Composable
fun Catalog(){

    var category by remember { mutableStateOf("Каталог") }

    val ListCateg: List<String> = listOf("Все","Популярные","Женщинам","Мужчинам","Детям","Аксессуары")
    var currentCategory by remember { mutableStateOf(ListCateg[0]) }

    var stateButton by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 20.dp)) {

        SpacerH(72)

        headerCatalog({}) { }

        SpacerH(32)

        categoryMenu(ListCateg, currentCategory,
            onClick = { currCateg ->
                currentCategory = currCateg
            })

        SpacerH(20)

        LazyColumn()
        {items(2){

            primaryCard("Рубашка Воскресенье для машинного вязания",
                "Мужская одежда",300,
                true,{stateButton = !stateButton})

            SpacerH(16)
        }
            item {

                SpacerH(42)
            }
        }


    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {

        if(stateButton){

            Box(modifier = Modifier.padding(bottom = 92.dp, start = 20.dp, end = 20.dp).height(120.dp).fillMaxWidth(),
                contentAlignment = Alignment.Center) {

                cartButton(500, {})

            }
        }

        Tabbar(category) {

        }
    }

}

@Preview
@Composable
fun PreviewCatalog(){

    Catalog()

}