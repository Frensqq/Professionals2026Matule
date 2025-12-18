package com.example.matule_2026.Presentation.Screen.Project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.matule_2026.Presentation.ViewModels.MainViewModel
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.matule_2026.R
import com.example.uikit.UI.Black
import com.example.uikit.UI.Description
import com.example.uikit.UI.InputBg
import com.example.uikit.UI.Typography
import com.example.uikit.buttons.bigButton
import com.example.uikit.components.SpacerH
import com.example.uikit.components.Tabbar
import com.example.uikit.inputs.inputAndTitle
import com.example.uikit.inputs.inputAndTitleDate
import com.example.uikit.selects.genderSelect
import com.example.uikit.selects.select

@Composable
fun CreateProject(navController: NavController, viewModel: MainViewModel){

    var state = viewModel.state

    var category by remember { mutableStateOf("Проекты") }
    var listTYPE = listOf<String>("Web", "Mobile","Desktop")
    var list = listOf<String>("Web", "Mobile","Desktop")
    var value by remember { mutableStateOf("") }



    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        item {

            SpacerH(72)

            Row(modifier = Modifier.fillMaxWidth().height(48.dp),
                horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Top) {

                Text("Создать проект", style = Typography().Title2_SemiBold, color = Black)

            }

            SpacerH(13)

            selectAndText("Тип", state.type,"Выберите  тип",listTYPE,
                {
                    viewModel.updateState(state.copy(type = it))
                } )

            SpacerH(16)

            inputAndTitle("Название проекта", state.name,false,false,
                "Введите имя", {
                    viewModel.updateState(state.copy(name = it))
                })
            SpacerH(16)

            inputAndTitleDate("Дата Начала", "--.--.----", state.dateStart,
                {
                    viewModel.updateState(state.copy(dateStart = it))
                })
            SpacerH(22)

            inputAndTitleDate("Дата Окончания", "--.--.----", state.dateEnd,
               {viewModel.updateState(state.copy(dateEnd = it))})
            SpacerH(10)

            var date by remember { mutableStateOf("") }

            SpacerH(10)

            genderSelect(state.gender) {
                viewModel.updateState(state.copy(gender = it))
            }

            SpacerH(16)

            inputAndTitle("Источник описания", state.description,
                false,false, "example.com", {
                    viewModel.updateState(state.copy(description = it))
                })

            SpacerH(17)

            selectAndText("Категория", state.category,
                "Выберите  категорию",list,{
                    viewModel.updateState(state.copy(category = it))
                } )

            SpacerH(37)

            Box(modifier = Modifier.height(192.dp).width(202.dp).
            clip(RoundedCornerShape(10.dp)).background(InputBg).
                clickable{

                },
                contentAlignment = Alignment.Center) {

                Icon(painter = painterResource(R.drawable.plus),
                    contentDescription = null, tint = Description
                )
            }

            SpacerH(32)

            bigButton("Подтвердить", true) {
                viewModel.createProject(navController)
            }

            SpacerH(103)

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

@Composable
fun selectAndText(titleText:String,value: String,text: String, selectOptions: List<String>,onSelect: (String) -> Unit){

    Column() {

        Text(titleText, style = Typography().Caption_Regular,
            color = Description)
        SpacerH(8)
        select(value, text, selectOptions) { currentSelect ->
            onSelect(currentSelect)
        }
    }

}

//@Preview
//@Composable
//fun PreviewCreateProject(){
//
//    CreateProject()
//}