package com.example.matule_2026.Presentation.State

import com.example.networklibrary.domain.model.User
import com.example.networklibrary.domain.model.UserAuth

data class AuthState (
    var email:String = "",
    var password:String = "",
    var passwordConfirm: String = "",

    val UserAuth: List<UserAuth> = emptyList(),
    val CurrentUser: User? = null,

//    var id:String ="",
//    var token:String ="",

    var name:String ="",
    var surname:String ="",
    var lastname:String ="",
    var gender:String ="",
    var dateBirthday:String ="",

    var isLoading:Boolean = false,
    var isSuccess:Boolean =false,
    var error: String?=null,
    var isNotInternet:Boolean = false
)