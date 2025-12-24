package com.example.matule_2026.Presentation.Screen.Main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
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
import com.example.uikit.UI.Black
import com.example.uikit.UI.Placeholders
import com.example.uikit.UI.Typography
import com.example.uikit.buttons.bigButton
import com.example.uikit.components.SpacerH
import com.example.uikit.modal.modal

@Composable
fun ProductDetails(description: String, name: String,onExit: () -> Unit, stateButton: Boolean, onClick:() -> Unit, cost:Int){

        Box(
            modifier = Modifier.fillMaxSize().background(Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.BottomCenter
        ) {

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {
                modal(name) {
                    onExit()
                }

                Column(modifier = Modifier.background(Color.White).padding(horizontal = 20.dp)) {
                    SpacerH(20)
                    Text("Описание", style = Typography().Headline_Medium, color = Placeholders)
                    SpacerH(8)

                    Text(
                        description,

                        )
                    SpacerH(49)
                    bigButton("Добавить за $cost ₽", stateButton) { onClick() }
                    SpacerH(40)
                }
            }
        }

}