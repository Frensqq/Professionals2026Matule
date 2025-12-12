package com.example.matule_2026.Presentation.screen.authorization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.uikit.buttons.bigButton
import com.example.uikit.components.SpacerH
import com.example.uikit.components.authorizationTitle
import com.example.uikit.inputs.inputAndTitle

@Composable
fun CreatePassword(){

    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var stateButton by remember { mutableStateOf(false) }


    Column(modifier = Modifier.fillMaxSize()
        .padding(top = 103.dp, bottom = 322.dp, start = 20.dp, end = 20.dp),
        Arrangement.SpaceBetween) {

        authorizationTitle("Создание пароля", "Введите новый пароль")


        Column() {

            inputAndTitle("Новый Пароль", password,true,
                false,"") { CurrentPass ->
                password = CurrentPass
            }
            SpacerH(12)
            inputAndTitle("Повторите пароль", password,true,
                false,"") { CurrentPass2 ->
                password2 = CurrentPass2
            }
            SpacerH(10)
            bigButton("Сохранить", stateButton) {

            }
        }
    }


}

@Preview
@Composable
fun PreviewCreatePassword(){

    CreatePassword()


}