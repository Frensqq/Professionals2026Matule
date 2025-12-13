package com.example.matule_2026.Presentation.Screen.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matule_2026.R
import com.example.uikit.UI.Black
import com.example.uikit.UI.Error
import com.example.uikit.UI.Placeholders
import com.example.uikit.UI.Typography
import com.example.uikit.components.SpacerH
import com.example.uikit.components.SpacerW
import com.example.uikit.components.Tabbar
import com.example.uikit.controls.toggle

@Composable
fun Profile(name: String, email: String){

    var category by remember { mutableStateOf("Профиль") }
    var stateToggle by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        SpacerH(76)

        Column(Modifier.padding(horizontal = 20.dp).fillMaxWidth()) {
            Text(name, style = Typography().Title1_ExtraBold, color = Black)

            SpacerH(8)

            Text(email, style = Typography().Headline_Regular, color = Placeholders)

            SpacerH(24)

            Row(modifier = Modifier.height(64.dp).clickable{

            },
                verticalAlignment = Alignment.CenterVertically){
                Image(bitmap = ImageBitmap.imageResource(R.drawable.zakaz),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp))

                SpacerW(20)

                Text("Мои заказы", style = Typography().Title3_Semibold,
                    color = Black)
            }

            Row(modifier = Modifier.fillMaxWidth().height(64.dp).padding(end=15.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Row(modifier = Modifier.height(64.dp),verticalAlignment = Alignment.CenterVertically){
                    Image(bitmap = ImageBitmap.imageResource(R.drawable.settings),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp))

                    SpacerW(20)

                    Text("Уведомления", style = Typography().Title3_Semibold,
                        color = Black)
                }


                toggle(stateToggle) {
                    stateToggle = !stateToggle
                }
            }

        }


        SpacerH(176)

        Column(modifier = Modifier.padding(horizontal = 78.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text("Политика конфиденциальности", style = Typography().Text_Medium,
                color = Placeholders,
                modifier = Modifier.clickable{

                })

            SpacerH(24)

            Text("Пользовательское соглашение", style = Typography().Text_Medium,
                color = Placeholders,
                modifier = Modifier.clickable{

                })

            SpacerH(24)

            Text("Выход", style = Typography().Text_Medium,
                color = Error,
                modifier = Modifier.clickable{

                })
        }


        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter) {

            Tabbar(category) {

            }
        }
    }
}


@Preview
@Composable
fun PreviewProfile(){
    Profile("Эдуард","afersfsr@dsfsr.ru")
}