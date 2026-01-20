package com.example.matule_2026.Presentation.Screen.Project

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.matule_2026.Presentation.ViewModels.MainViewModel
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.matule_2026.R
import com.example.uikit.UI.Black
import com.example.uikit.UI.Description
import com.example.uikit.UI.InputBg
import com.example.uikit.UI.Typography
import com.example.uikit.buttons.bigButton
import com.example.uikit.components.SpacerH
import com.example.uikit.components.TabBar
import com.example.uikit.inputs.inputAndTitle
import com.example.uikit.inputs.inputAndTitleDate
import com.example.uikit.selects.Select


@Composable
fun CreateProject(navController: NavController, viewModel: MainViewModel){

    var state = viewModel.state
    val context = LocalContext.current

    var category by remember { mutableStateOf("Проекты") }

    if (category == "Главная"){
        navController.navigate(NavigationRoutes.MAIN)
    }
    else if (category == "Каталог"){
        navController.navigate(NavigationRoutes.CATALOG)
    }
    else if (category == "Профиль"){
        navController.navigate(NavigationRoutes.PROFILE)
    }

    var listTYPE = listOf<String>("Web", "Mobile","Desktop")
    var list = listOf<String>("Web", "Mobile","Desktop")
    var genderList = listOf<String>("Мужской", "Женский","Другое")

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.selectImage(it, context)
        }
    }

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

            Select(state.gender, "Пол",genderList) {
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

            Box(modifier = Modifier.height(192.dp).width(202.dp)
                .clip(RoundedCornerShape(10.dp)).background(InputBg)
                .clickable {
                    galleryLauncher.launch("image/*")
                },
                contentAlignment = Alignment.Center) {

                if (viewModel.selectedImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(model = viewModel.selectedImageUri),
                        contentDescription = "Selected image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.plus),
                        contentDescription = "Добавить фото",
                        tint = Description
                    )
                }
            }

            SpacerH(16)

            if (viewModel.selectedImageUri != null) {
                Text(
                    text = "Фото выбрано",
                    style = Typography().Caption_Regular,
                    color = Description,

                )
                SpacerH(16)
            }

            SpacerH(16)

            bigButton("Подтвердить", true) {
                if (viewModel.selectedImageUri != null) {
                    viewModel.createProjectWithImage(navController, context)
                } else {
                    viewModel.createProject(navController)
                }
            }

            SpacerH(103)

        }
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        TabBar(category,
            { currentCategory ->
                category = currentCategory
            }
        )
    }
}

@Composable
fun selectAndText(titleText:String,value: String,text: String, selectOptions: List<String>,onSelect: (String) -> Unit){

    Column() {

        Text(titleText, style = Typography().Caption_Regular,
            color = Description)
        SpacerH(8)
        Select (value, text, selectOptions) { currentSelect ->
            onSelect(currentSelect)
        }
    }

}