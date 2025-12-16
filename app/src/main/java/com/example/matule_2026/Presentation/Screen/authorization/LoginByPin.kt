package com.example.matule_2026.Presentation.Screen.authorization

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.matule_2026.Domain.Repository.UserRepository
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.uikit.UI.Black
import com.example.uikit.UI.Typography
import com.example.uikit.components.SpacerH
import com.example.uikit.keyboard.keyBoard

@Composable
fun LoginByPin(navController: NavController){

    var pinArray by remember { mutableStateOf(mutableListOf<Int>()) }



    Column(modifier = Modifier.fillMaxSize()) {
        SpacerH(144)

        Box(modifier = Modifier.padding(start = 50.dp,end = 49.dp)
            .fillMaxWidth().height(64.dp), contentAlignment = Alignment.Center) {

            Column {
                Text(
                    "Вход", style = Typography().Title1_ExtraBold,
                    color = Black, textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()

                    )

            }
        }


        SpacerH(132)

        Box(modifier = Modifier.fillMaxSize().padding(bottom = 80.dp), contentAlignment = Alignment.BottomCenter) {

            keyBoard { currentPin ->
                pinArray = currentPin.toMutableList()
            }
        }

        if (UserRepository.validatePinCode(pinArray.joinToString(""))){
            navController.navigate(NavigationRoutes.MAIN)
        }
    }
}

//@Preview
//@Composable
//fun PreviewloginByPin(){
//
//    LoginByPin()
//
//}