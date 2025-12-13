package com.example.matule_2026.Presentation.Screen.Project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.R
import com.example.uikit.UI.Black
import com.example.uikit.UI.InputIcon
import com.example.uikit.UI.Placeholders
import com.example.uikit.UI.Typography
import com.example.uikit.cards.projectCard
import com.example.uikit.components.SpacerH
import com.example.uikit.components.SpacerW
import com.example.uikit.components.Tabbar

@Composable
fun Projects(){

    var category by remember { mutableStateOf("Проекты") }



    Column(modifier = Modifier.fillMaxSize()) {

        SpacerH(72)
        Row(modifier = Modifier.fillMaxWidth().height(48.dp).padding(horizontal = 20.dp),
            verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween) {

            SpacerW(19)

            Text("Проекты", style = Typography().Title2_SemiBold,
                color = Black, modifier = Modifier.padding(top = 2.dp))

            Icon(painter = painterResource(R.drawable.icon_plus),
                contentDescription = null, modifier = Modifier.padding(top = 6.dp).size(20.dp),
                tint = Placeholders
            )

        }
        SpacerH(18)

        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp),

        )
        {items(5){
            projectCard("Мой первый проект", "2") {

            }
            SpacerH(16)
        }
            item {
                SpacerH(72)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        Tabbar(category) {
                currentCat ->
            category = currentCat
        }
    }

}

@Preview
@Composable
fun PreviewProjects(){
    Projects()
}