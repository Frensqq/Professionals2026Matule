package com.example.matule_2026.Presentation.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matule_2026.Domain.Repository.UserRepository
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.toString


class SplashViewModel(): ViewModel() {

    fun launch(controller: NavHostController){
        viewModelScope.launch {
            delay(2000)
            Log.e("UserRepository.act Splash", UserRepository.act.toString())
            //Не авторизован
            if (UserRepository.act==0){
                controller.navigate(NavigationRoutes.SIGNINPASS){
                    popUpTo(NavigationRoutes.SPLASH){
                        inclusive=true
                    }
                }
            }

            //авторизован
            if (UserRepository.act==1){
                controller.navigate(NavigationRoutes.SIGNINPIN){
                    popUpTo(NavigationRoutes.SIGNINPASS){
                        inclusive=true
                    }
                }
            }

        }
    }
}