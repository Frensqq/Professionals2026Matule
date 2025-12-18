package com.example.matule_2026.Presentation.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.matule_2026.Domain.Repository.UserRepository
import com.example.matule_2026.Domain.UseCase.UseCase

import com.example.matule_2026.Presentation.State.MainState
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.networklibrary.data.remoute.PBApiServis
import com.example.networklibrary.domain.model.NetworkResult
import com.example.networklibrary.domain.model.Product
import com.example.networklibrary.domain.model.RequestCart
import com.example.networklibrary.domain.model.RequestOrder
import com.example.networklibrary.domain.model.RequestProject
import com.example.networklibrary.domain.model.ResponseCart
import com.example.networklibrary.domain.model.ResponseOrder
import com.example.uikit.components.WarningWindow
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: UseCase): ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: MainState get() = _state.value

    fun updateState(newstate: MainState) {
        _state.value = newstate
    }


    fun getImage(collectionId:String, id: String, image:String): String {
        val imageUrl = PBApiServis.getImageUrl(collectionId,id,image)
        return imageUrl
    }

    fun getNews() {

        viewModelScope.launch {
            try {
                when (val result = useCase.GetNews()) {
                    is NetworkResult.Error -> {
                        Log.d("RAW_RESPONSE", result.error.toString())
                        Log.d("Ошибка Получения акций и новостей", result.error.message)
                    }
                    is NetworkResult.Success -> {
                        Log.d("NetworkResponse", "Success: ${result.data}")
                        /*       val title = result.data.items?.map { it.title }*/

                        updateState(state.copy(
                            listNews = result.data.items?: emptyList())
                        )
                    }
                    is NetworkResult.NoInternet -> {
                        Log.d("Ошибка Получения акций и новостей", "Нет интернета")
                    }
                }

            } catch (e: Exception) {
                Log.d("Ошибка Получения акций и новостей", e.message.toString())
            }
        }
    }


    fun getProduct(filter: String? = null) {
        viewModelScope.launch {
            try {
                when (val result = useCase.getProduct(filter)) {
                    is NetworkResult.Error -> {
                        Log.i("Ошибка Продукты", result.error.message.toString())
                    }
                    is NetworkResult.Success -> {
                        Log.d("Продукты", result.data.items.toString())

                        updateState(state.copy(listProduct = result.data?.items ?: emptyList()))
                        Log.e("Продукты state", state.listProduct.toString())
                    }
                    is NetworkResult.NoInternet -> {}
                }
            } catch (e: Exception) {
                Log.i("Ошибка Продукты", e.message.toString())
            }
        }
    }

    fun getProject(filter: String? = null) {
        viewModelScope.launch {
            try {
                when (val result = useCase.getProject(filter)) {
                    is NetworkResult.Error -> {
                        Log.i("Ошибка вывод проектов", result.error.message.toString())
                    }
                    is NetworkResult.Success -> {
                        Log.d("Проекты ", result.data.items.toString())

                        updateState(state.copy(listProject = result.data?.items ?: emptyList()))
                        Log.e("Проекты вывод state", state.listProduct.toString())
                    }
                    is NetworkResult.NoInternet -> {}
                }
            } catch (e: Exception) {
                Log.i("Ошибка вывод проектов", e.message.toString())
            }
        }
    }

    fun createOrders(product_id: String, count: Int) {
        viewModelScope.launch {
            try {
                when (val result = useCase.CreateOrders(request = RequestOrder(
                    UserRepository.UserID,
                            product_id, count
                ) )) {
                    is NetworkResult.Error -> {
                        Log.i("Ошибка создание заказа", result.error.message.toString())
                    }

                    is NetworkResult.Success -> {
                        Log.d("Создание заказа", result.data.id)

                        updateState(state.copy(Order = result.data?: null ))
                        Log.e("Создание заказа state", state.listProduct.toString())
                    }
                    is NetworkResult.NoInternet -> {}
                }
            } catch (e: Exception) {
                Log.i("Ошибка Создание заказа", e.message.toString())
            }
        }
    }

    fun createProject(navController: NavController) {
        viewModelScope.launch {
            try {
                when (val result = useCase.CreateProject(request = RequestProject(
                  title = state.name,
                    typeProject = state.type,
                    user_id = UserRepository.UserID,
                    dateStart = state.dateStart,
                    dateEnd = state.dateEnd,
                    gender = state.gender,
                    category = state.category,
                    description_source = state.description
                ) )) {
                    is NetworkResult.Error -> {
                        Log.i("Ошибка создание проекта", result.error.message.toString())
                    }

                    is NetworkResult.Success -> {
                        Log.d("Создание проекта", result.data.id)

                        updateState(state.copy(project = result.data?: null ))

                        navController.navigate(NavigationRoutes.PROJECTS)
                        Log.e("Создание проекта state", state.listProduct.toString())
                    }
                    is NetworkResult.NoInternet -> {}
                }
            } catch (e: Exception) {
                Log.i("Ошибка Создание проекта", e.message.toString())
            }
        }
    }

    fun addCart(product_id: String) {
        viewModelScope.launch {
            try {
                when (val result = useCase.AddCart(request = RequestCart(
                    UserRepository.UserID,
                    product_id,1
                ) )) {
                    is NetworkResult.Error -> {
                        Log.i("Ошибка добавления в корзину", result.error.message.toString())
                    }

                    is NetworkResult.Success -> {
                        Log.d("Добавление в корзину", result.data.id)

                        updateState(state.copy(Cart = result.data?: null ))
                        Log.e("Добавление в корзину state", state.listProduct.toString())
                    }
                    is NetworkResult.NoInternet -> {}
                }
            } catch (e: Exception) {
                Log.i("Ошибка добавления в корзину", e.message.toString())
            }
        }
    }

    fun changeCart(id_bucket: String, product_id: String, count:Int) {
        viewModelScope.launch {
            try {
                when (val result = useCase.changeCart(id_bucket, request = RequestCart(
                    UserRepository.UserID,
                    product_id,count
                )
                )) {
                    is NetworkResult.Error -> {
                        Log.i("Ошибка редактирования корзины", result.error.message.toString())
                    }

                    is NetworkResult.Success -> {
                        Log.d("Редактирование корзины", result.data.id)

                        updateState(state.copy(Cart = result.data?: null ))
                        Log.e("Редактирование корзины state", state.listProduct.toString())
                    }
                    is NetworkResult.NoInternet -> {}
                }
            } catch (e: Exception) {
                Log.i("Ошибка редактирования корзины", e.message.toString())
            }
        }
    }

    fun viewCart() {
        viewModelScope.launch {
            try {
                when (val result = useCase.viewCart("user_id = '${UserRepository.UserID}'" )) {
                    is NetworkResult.Error -> {
                        Log.i("Ошибка просмотра в корзину", result.error.message.toString())
                    }

                    is NetworkResult.Success -> {
                        Log.d("Просмотр корзины", result.data.items.toString())

                        updateState(state.copy(listCart = result.data.items ?: emptyList() ))
                        Log.e("Просмотр корзины state", state.listCart.toString())
                    }
                    is NetworkResult.NoInternet -> {}
                }
            } catch (e: Exception) {
                Log.i("Ошибка просмотра корзины", e.message.toString())
            }
        }
    }



    fun deleteCart(id: String) {
        viewModelScope.launch {
            try {
                when (val result = useCase.deleteCart(id
                )) {
                    is NetworkResult.Error -> {
                        Log.i("Ошибка удаления в корзину", result.error.message.toString())
                    }

                    is NetworkResult.Success -> {
                        Log.d("Удаление в корзину", "Success")

                        updateState(state.copy(Cart =  null ))
                        Log.e("Удаление в корзину state", state.listProduct.toString())
                    }
                    is NetworkResult.NoInternet -> {}
                }
            } catch (e: Exception) {
                Log.i("Ошибка удаления в корзину", e.message.toString())
            }
        }
    }


//    fun getUser() {
//
//        viewModelScope.launch {
//            updateState(state.copy(isLoading = true, error = null))
//            try {
//                when (val result = useCase.getProfile(UserRepository.UserID)) {
//                    is NetworkResult.Error -> {
//
//                        updateState(state.copy(isLoading = false, error = result.error.message))
//                        Log.d("Profile",result.error.message)
//                        Log.d("Profile1",result.error.toString())
//                    }
//
//                    is NetworkResult.Success -> {
//
//                        Log.d("Profile",result.data.toString())
//
//                        updateState(
//                            state.copy(
//                                name = result.data.firstname,
//                            )
//                        )
//                        updateState(state.copy(isLoading = false, error = null))
//
//                    }
//
//                    is NetworkResult.NoInternet -> {}
//
//                }
//
//            } catch (e: Exception) {
//                updateState(state.copy(isLoading = false, error = e.message.toString()))
//            }
//        }
//    }
}