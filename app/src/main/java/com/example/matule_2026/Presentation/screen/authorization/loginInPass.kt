package com.example.matule_2026.Presentation.screen.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.UI.Accent
import com.example.uikit.UI.Typography
import com.example.uikit.UI.White
import com.example.uikit.buttons.LogIn
import com.example.uikit.buttons.bigButton
import com.example.uikit.components.SpacerH
import com.example.uikit.components.authorizationTitle
import com.example.uikit.inputs.inputAndTitle

@Composable
fun LoginInPass(){

    var state by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        SpacerH(59)

        authorizationTitle("Добро пожаловать!",
            "Войдите, чтобы пользоваться функциями приложения")

        SpacerH(64)

        inputAndTitle("Вход по E-mail", email,false,
            false,"example@mail.com") { CurrentEmail ->
            email = CurrentEmail
        }

        SpacerH(14)

        inputAndTitle("Пароль", password,true,
            false,"") {CurrentPass ->
            password = CurrentPass
        }
        SpacerH(14)

        bigButton("Далее", state) {

        }
        SpacerH(15)
        Text("Зарегистрироваться",
            style = Typography().Text_Regular,
            color = Accent,
            modifier = Modifier.clickable{

            })



        Box(modifier = Modifier.fillMaxSize().padding(bottom = 56.dp), contentAlignment = Alignment.BottomCenter) {

            LogIn({}, {})
        }



    }


}

@Preview
@Composable
fun PreviewloginInPass(){

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        LoginInPass()
    }

}