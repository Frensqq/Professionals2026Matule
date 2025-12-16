package com.example.matule_2026.Presentation.Screen.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.matule_2026.Presentation.ViewModels.AuthViewModel
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.uikit.UI.Accent
import com.example.uikit.UI.Typography
import com.example.uikit.buttons.LogIn
import com.example.uikit.buttons.bigButton
import com.example.uikit.components.SpacerH
import com.example.uikit.components.authorizationTitle
import com.example.uikit.inputs.inputAndTitle

@Composable
fun LoginInPass(navController: NavHostController, viewModel: AuthViewModel){

    val state = viewModel.state

    var isNotNull = if(state.email!="" && state.password!= "") true else false

    Column(modifier = Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        SpacerH(59)

        authorizationTitle("Добро пожаловать!",
            "Войдите, чтобы пользоваться функциями приложения")

        SpacerH(64)

        inputAndTitle("Вход по E-mail", state.email,false,
            !state.error.isNullOrEmpty(),"example@mail.com")
        { viewModel.updateState(state.copy(email = it))}

        SpacerH(14)

        inputAndTitle("Пароль", state.password,true,
            !state.error.isNullOrEmpty() ,"")
        { viewModel.updateState(state.copy(password = it))}
        SpacerH(14)

        bigButton("Далее", isNotNull) {
            viewModel.Auth(state.email,state.password,navController)
        }
        SpacerH(15)
        Text("Зарегистрироваться",
            style = Typography().Text_Regular,
            color = Accent,
            modifier = Modifier.clickable{
                navController.navigate(NavigationRoutes.CREATEPROFILE){
                }
            })


        Box(modifier = Modifier.fillMaxSize().padding(bottom = 56.dp), contentAlignment = Alignment.BottomCenter) {
            LogIn({}, {})
        }



    }


}

//@Preview
//@Composable
//fun PreviewloginInPass(){
//
//    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
//        LoginInPass()
//    }
//
//}