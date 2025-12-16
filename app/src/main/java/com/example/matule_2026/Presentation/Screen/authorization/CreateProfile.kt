package com.example.matule_2026.Presentation.Screen.authorization

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.matule_2026.Presentation.ViewModels.AuthViewModel
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.uikit.UI.Black
import com.example.uikit.UI.Placeholders
import com.example.uikit.UI.Typography
import com.example.uikit.buttons.bigButton
import com.example.uikit.components.SpacerH
import com.example.uikit.inputs.Date
import com.example.uikit.inputs.inputAndTitleDate
import com.example.uikit.inputs.textInputField
import com.example.uikit.selects.genderSelect

@Composable
fun CreateProfile(navController: NavHostController, viewModel:AuthViewModel){

    val state = viewModel.state
    LaunchedEffect(Unit) {
        Log.d("44",state.dateBirthday)
    }


    var isNotNull = if(state.name!= "" && state.surname!= ""&& state.lastname!= ""&& state.gender!= ""&& state.dateBirthday!= "") true else false

    Column(modifier = Modifier.fillMaxSize()
        .padding(horizontal = 20.dp)) {

        SpacerH(76)

        Text("Создание Профиля", style = Typography().Title1_ExtraBold, color = Black)

        SpacerH(44)

        Text("Без профиля вы не сможете создавать проекты.",
            style =  Typography().Caption_Regular, color = Placeholders
        )

        SpacerH(8)

        Text("В профиле будут храниться результаты проектов и ваши описания.",
            style =  Typography().Caption_Regular, color = Placeholders
        )

        SpacerH(32)

        textInputField(state.name,false,false,"Имя") {
            viewModel.updateState(state.copy(name = it))
        }
        SpacerH(24)
        textInputField(state.lastname,false,false,"Отчество") {
            viewModel.updateState(state.copy(lastname = it))
        }
        SpacerH(24)
        textInputField(state.surname,false,false,"Фамилия") {
            viewModel.updateState(state.copy(surname = it))
        }
        SpacerH(24)
        Date("Дата рождения",state.dateBirthday,{
            viewModel.updateState(state.copy(dateBirthday = it))
        })

//        textInputField(state.dateBirthday,false,false,"Дата рождения") {
//            viewModel.updateState(state.copy(dateBirthday = it))
//        }
        SpacerH(24)

        genderSelect(state.gender) {
            viewModel.updateState(state.copy(gender = it))
        }

        SpacerH(24)
        textInputField(state.email,false,false,"Почта") {
            viewModel.updateState(state.copy(email = it))
        }


        Box(modifier = Modifier.fillMaxSize().padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter) {

            bigButton("Далее",isNotNull) {
                navController.navigate(NavigationRoutes.CREATEPASS)
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewCreateProfile(){
//    CreateProfile()
//}