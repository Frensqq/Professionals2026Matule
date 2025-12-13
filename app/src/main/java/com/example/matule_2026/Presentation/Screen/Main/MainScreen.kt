package com.example.matule_2026.Presentation.Screen.Main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.UI.Accent
import com.example.uikit.UI.Placeholders
import com.example.uikit.UI.Typography
import com.example.uikit.cards.primaryCard
import com.example.uikit.cards.projectCard
import com.example.uikit.components.SpacerH
import com.example.uikit.components.SpacerW
import com.example.uikit.components.Tabbar
import com.example.uikit.components.categoryMenu
import com.example.uikit.search.searchField

@Composable
fun MainScreen(){

    var category by remember { mutableStateOf("Главная") }
    var searchString by remember { mutableStateOf("") }

    val ListCateg: List<String> = listOf("Все","Популярные","Женщинам","Мужчинам","Детям","Аксессуары")

    var currentCategory by remember { mutableStateOf(ListCateg[0]) }

    Column(modifier = Modifier.fillMaxSize().padding(start = 20.dp)) {

        SpacerH(68)

        Box(modifier = Modifier.padding(end = 20.dp)) {
            searchField { currSearch ->
                searchString = currSearch
            }
        }

        SpacerH(32)

        Text("Акции и новости", style = Typography().Title3_Semibold,
            color = Placeholders)

        SpacerH(16)

        LazyRow() {
            items(10){
                Box(modifier = Modifier.width(270.dp).height(152.dp)
                    .clip(RoundedCornerShape(12.dp)).background(Accent))

                SpacerW(16)
            }
        }

        SpacerH(32)

        Text("Каталог описаний", style = Typography().Title3_Semibold,
            color = Placeholders)

        SpacerH(15)

        categoryMenu(ListCateg, currentCategory,
            onClick = { currCateg ->
                currentCategory = currCateg
            })

        SpacerH(25)

        LazyColumn(modifier = Modifier.padding(end = 20.dp))
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
fun PreviewMainScreen(){
    MainScreen()
}