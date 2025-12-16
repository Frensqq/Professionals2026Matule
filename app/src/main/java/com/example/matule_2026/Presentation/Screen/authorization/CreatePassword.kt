package com.example.matule_2026.Presentation.Screen.authorization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.matule_2026.Presentation.ViewModels.AuthViewModel
import com.example.uikit.buttons.bigButton
import com.example.uikit.components.SpacerH
import com.example.uikit.components.authorizationTitle
import com.example.uikit.inputs.inputAndTitle

@Composable
fun CreatePassword(navController: NavHostController, viewModel: AuthViewModel){

    var stateButton by remember { mutableStateOf(true) }

    val state = viewModel.state
    var isNotNull = if(state.email!="" && state.password!= "") true else false

    Column(modifier = Modifier.fillMaxSize()
        .padding(top = 103.dp, bottom = 322.dp, start = 20.dp, end = 20.dp),
        Arrangement.SpaceBetween) {

        authorizationTitle("Создание пароля", "Введите новый пароль")


        Column() {

            inputAndTitle("Новый Пароль", state.password,true,
                false,"") {
                viewModel.updateState(state.copy(password = it))
            }
            SpacerH(12)
            inputAndTitle("Повторите пароль", state.passwordConfirm,true,
                false,"") {
                viewModel.updateState(state.copy(passwordConfirm = it))
            }
            SpacerH(10)
            bigButton("Сохранить", stateButton) {
                    viewModel.Registration(navController)
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewCreatePassword(){
//
//    CreatePassword()
//
//
//}