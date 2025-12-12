package com.example.matule_2026.Presentation.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.uikit.UI.Typography
import com.example.uikit.UI.White

@Composable
fun Splash(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF74C8E4),
                        Color(0xFF2254F5),  
                        Color(0xFF74C8E4)
                    ),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            ), contentAlignment = Alignment.Center
    )
    {
        Text(
            "Matule",
            style = Typography().Caption2_Regular,
            fontSize = 40.sp,
            color = White
        )
    }


}

@Preview
@Composable
fun PreviewSplash(){

    Splash()

}