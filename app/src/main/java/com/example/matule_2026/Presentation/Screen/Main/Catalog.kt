package com.example.matule_2026.Presentation.Screen.Main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
        {items(5){

            primaryCard("Рубашка Воскресенье для машинного вязания",
                "Мужская одежда",300,
                true,{})

            SpacerH(16)
        }
            item {
                SpacerH(42)
            }
        }

    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {

        Tabbar(category) {

        }
    }

}

@Preview
@Composable
fun PreviewCatalog(){

    Catalog()

}