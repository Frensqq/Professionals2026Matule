package com.example.matule_2026.Presentation.screen.authorization

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.uikit.buttons.bigButton
import com.example.uikit.components.SpacerH
import com.example.uikit.components.authorizationTitle
import com.example.uikit.inputs.inputAndTitle

@Composable
fun loginInPass(){

    var state by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column {
        SpacerH(103)

        authorizationTitle("Добро пожаловать!",
            "Войдите, чтобы пользоваться функциями приложения")

        SpacerH(64)

        inputAndTitle("Вход по E-mail", "",false,
            false,"example@mail.com") {

        }

        SpacerH(14)

        inputAndTitle("Пароль", "",true,
            false,"") {}
        SpacerH(14)

        bigButton("Далее", state) { }


    }


}

@Preview
@Composable
fun PreviewloginInPass(){

    loginInPass()

}