package com.example.matule_2026.Presentation.navigate

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matule_2026.Presentation.Screen.Main.Cart
import com.example.matule_2026.Presentation.Screen.Main.Catalog
import com.example.matule_2026.Presentation.Screen.Main.MainScreen
import com.example.matule_2026.Presentation.Screen.Profile.Profile
import com.example.matule_2026.Presentation.Screen.Project.CreateProject
import com.example.matule_2026.Presentation.Screen.Project.Projects
import com.example.matule_2026.Presentation.Screen.authorization.CreatePassword
import com.example.matule_2026.Presentation.Screen.authorization.CreatePin
import com.example.matule_2026.Presentation.Screen.authorization.CreateProfile
import com.example.matule_2026.Presentation.Screen.authorization.LoginByPin
import com.example.matule_2026.Presentation.Screen.authorization.LoginInPass
import com.example.matule_2026.Presentation.Screen.splash.Splash
import com.example.matule_2026.Presentation.ViewModels.AuthViewModel
import com.example.matule_2026.Presentation.ViewModels.MainViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun Navigation(isOnline: Boolean) {

    val navController = rememberNavController()

    val AuthViewModel: AuthViewModel  = koinViewModel()
    val MainViewModel: MainViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = NavigationRoutes.SPLASH) {

        composable(NavigationRoutes.SPLASH){
            Splash(navController)
        }
        composable(NavigationRoutes.CART){
            Cart(navController, MainViewModel)
        }
        composable(NavigationRoutes.CATALOG){
            Catalog(navController, MainViewModel)
        }
        composable(NavigationRoutes.CREATEPASS){
            CreatePassword(navController, AuthViewModel)
        }
        composable(NavigationRoutes.CREATEPIN){
            CreatePin(navController)
        }
        composable(NavigationRoutes.CREATEPROFILE){
            CreateProfile(navController, AuthViewModel)
        }
        composable(NavigationRoutes.CREATEPROJECTS){
            CreateProject(navController, MainViewModel)
        }
        composable(NavigationRoutes.MAIN){
            MainScreen(navController,MainViewModel)
        }
        composable(NavigationRoutes.PROFILE){
            Profile(navController, AuthViewModel)
        }
        composable(NavigationRoutes.PROJECTS){
            Projects(navController, MainViewModel)
        }
        composable(NavigationRoutes.SIGNINPASS){
            LoginInPass(navController, AuthViewModel)
        }
        composable(NavigationRoutes.SIGNINPIN){
            LoginByPin(navController)
        }
    }
}