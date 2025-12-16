package com.example.matule_2026.Presentation.Screen.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.matule_2026.Domain.Repository.UserRepository
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.uikit.UI.Black
import com.example.uikit.UI.Placeholders
import com.example.uikit.UI.Typography
import com.example.uikit.components.SpacerH
import com.example.uikit.keyboard.ballonsAndKeyboard
import kotlin.collections.joinToString

@Composable
fun CreatePin(navController: NavController){

    var pinCode by remember { mutableStateOf(listOf<Int>()) }

    Column(modifier = Modifier.fillMaxSize()) {

        SpacerH(144)

        Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Создайте пароль",
                style = Typography().Title1_ExtraBold, color = Black
            )
            SpacerH(16)
            Text(
                "Для защиты ваших персональных данных",
                style = Typography().Text_Regular, color = Placeholders
            )
        }

        Box(modifier = Modifier.fillMaxSize().padding(bottom = 80.dp),
            contentAlignment = Alignment.BottomCenter) {

            Box(modifier = Modifier.height(468.dp)) {
                ballonsAndKeyboard {
                    CurrentPin->
                    pinCode = CurrentPin
                }
            }
        }
    }

    if (pinCode.size == 4){
        UserRepository.pinCode = pinCode.joinToString("")
        navController.navigate(NavigationRoutes.MAIN)
    }

}


//@Preview
//@Composable
//fun PreviewCreatePin(){
//
//    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
//
//        CreatePin()
//    }
//
//}