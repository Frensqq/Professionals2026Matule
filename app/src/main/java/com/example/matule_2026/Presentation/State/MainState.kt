package com.example.matule_2026.Presentation.State


import com.example.networklibrary.domain.model.News
import com.example.networklibrary.domain.model.Product
import com.example.networklibrary.domain.model.ProductItem
import com.example.networklibrary.domain.model.Project
import com.example.networklibrary.domain.model.ResponseCart
import com.example.networklibrary.domain.model.ResponseOrder
import com.example.networklibrary.domain.model.ResponsesCart

data class MainState (

    val listProduct: List<ProductItem> = emptyList(),
    val listProject: List<Project> = emptyList(),
    val listNews: List<News> = emptyList(),
    val project: Project? = null,

    val listOrders: List<ResponseOrder> = emptyList(),
    val Order: ResponseOrder? = null,

    var listCart: List<ResponseCart> = emptyList(),
    var Cart: ResponseCart? = null,



    val TabSelected:Int = 0,
    val notification: Boolean = false,


    var isLoading:Boolean = false,
    var isSuccess:Boolean =false,
    var error: String?=null,
    var isNotInternet:Boolean = false
)