package com.example.matule_2026.Presentation.Screen.authorization

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.UI.Black
import com.example.uikit.UI.Placeholders
import com.example.uikit.UI.Typography
import com.example.uikit.buttons.bigButton
import com.example.uikit.components.SpacerH
import com.example.uikit.inputs.textInputField
import com.example.uikit.selects.genderSelect

@Composable
fun CreateProfile(){

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var stateButton by remember { mutableStateOf(false) }



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

        textInputField(name,false,false,"Имя") {
            CurrentName -> name = CurrentName
        }
        SpacerH(24)
        textInputField(patronymic,false,false,"Отчество") {
                CurrentPatronymic -> patronymic = CurrentPatronymic
        }
        SpacerH(24)
        textInputField(surname,false,false,"Фамилия") {
                CurrentSurname -> surname = CurrentSurname
        }
        SpacerH(24)
        textInputField(date,false,false,"Дата рождения") {
                CurrentDate -> date = CurrentDate
        }
        SpacerH(24)

        genderSelect(gender) {
                CurrentGender -> gender = CurrentGender
        }

        SpacerH(24)
        textInputField(email,false,false,"Почта") {
                CurrentEmail -> email = CurrentEmail
        }


        Box(modifier = Modifier.fillMaxSize().padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter) {

            bigButton("Далее",stateButton) { }
        }
    }
}

@Preview
@Composable
fun PreviewCreateProfile(){
    CreateProfile()
}