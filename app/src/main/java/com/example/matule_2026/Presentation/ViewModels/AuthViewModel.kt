package com.example.matule_2026.Presentation.ViewModels

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matule_2026.Domain.Repository.UserRepository
import com.example.matule_2026.Domain.UseCase.UseCase
import com.example.matule_2026.Domain.patterns.isEmailValid
import com.example.matule_2026.Presentation.State.AuthState
import com.example.matule_2026.Presentation.navigate.NavigationRoutes
import com.example.networklibrary.domain.model.NetworkResult
import kotlinx.coroutines.launch

class AuthViewModel(private val UseCase: UseCase): ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: AuthState get() = _state.value

    fun updateState(newState: AuthState) {
        _state.value = newState
    }

    fun Auth(email: String, password: String, controller: NavHostController) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            if (email.isEmailValid()) {
                if (password.length >= 8) {
                    when(val res = UseCase(email, password)) {
                        is NetworkResult.Success -> {
                            // Теперь используем data.record.id из вашей библиотеки
                            UserRepository.UserID = res.data.record.id
                            UserRepository.Token = res.data.token
                            UserRepository.act = 1
                            Log.e("ACT", UserRepository.act.toString())
                            Log.e("UserRepository.UserID", UserRepository.UserID)
                            updateState(state.copy(email = "", password = ""))
                            controller.navigate(NavigationRoutes.CREATEPIN)
                        }
                        is NetworkResult.Error -> {
                            _state.value = _state.value.copy(isLoading = false, error = res.error.message)
                            if(res.error.message == "Нет такого пользователя") {
                                _state.value = _state.value.copy(email = email, password = password)
                                controller.navigate(NavigationRoutes.CREATEPIN)
                            }
                        }
                        is NetworkResult.NoInternet -> {
                            _state.value = _state.value.copy(error = "No Internet")
                        }
                    }
                    Log.d("auth", state.error.toString())
                } else {
                    Log.d("auth", "8")
                    _state.value = _state.value.copy(error = "Пароль меньше 8 символов")
                }
            } else {
                Log.d("auth", "email")
                _state.value = _state.value.copy(error = "Email не соответствует патерну")
            }
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            updateState(state.copy(isLoading = true, error = null))
            try {
                when(val response = UseCase.getUser(
                    UserRepository.UserID
                )) {
                    is NetworkResult.Success -> {
                        // Теперь используем data.id из вашей библиотеки
                        UserRepository.UserID = response.data.id
                        updateState(state.copy(
                            CurrentUser = response.data?: null)
                        )

                    }
                    is NetworkResult.Error -> {
                        updateState(state.copy(isLoading = false, error = response.error.message))
                    }
                    is NetworkResult.NoInternet -> {
                        updateState(state.copy(isNotInternet = true))
                    }
                }
                Log.d("Reg", state.error.toString())
            } catch (e: Exception) {
                Log.e("reg ViewModel", e.message.toString())
            }
        }
    }


    fun Registration(controller: NavHostController) {
        viewModelScope.launch {
            updateState(state.copy(isLoading = true, error = null))
            try {
                when(val response = UseCase.invoke(
                    state.email, state.password, state.password,
                    state.name, state.surname, state.lastname,
                    state.dateBirthday, state.gender
                )) {
                    is NetworkResult.Success -> {
                        // Теперь используем data.id из вашей библиотеки
                        UserRepository.UserID = response.data.id
                        UserRepository.email = state.email

                        controller.navigate(NavigationRoutes.SIGNINPASS)
                    }
                    is NetworkResult.Error -> {
                        updateState(state.copy(isLoading = false, error = response.error.message))
                    }
                    is NetworkResult.NoInternet -> {
                        updateState(state.copy(isNotInternet = true))
                    }
                }
                Log.d("Reg", state.error.toString())
            } catch (e: Exception) {
                Log.e("reg ViewModel", e.message.toString())
            }
        }
    }

    fun returnIdToken(){
        viewModelScope.launch {
            updateState(state.copy(isLoading = true, error = null))
            try {
                when(val response = UseCase.returnIdToken(
                    UserRepository.Token,
                )) {
                    is NetworkResult.Success -> {
                        Log.d("Success", response.data.items.toString())

                        updateState(state.copy(UserAuth = response.data?.items ?: emptyList()))
                        Log.e("returnIdToken state", state.UserAuth.toString())
                    }
                    is NetworkResult.Error -> {
                        updateState(state.copy(isLoading = false, error = response.error.message))
                    }
                    is NetworkResult.NoInternet -> {
                        updateState(state.copy(isNotInternet = true))
                    }
                }
                Log.d("returnIdToken", state.error.toString())
            } catch (e: Exception) {
                Log.e("returnIdToken ViewModel", e.message.toString())
            }
        }
    }

    fun logout(controller: NavHostController, id_logout: String){
        viewModelScope.launch {
            updateState(state.copy(isLoading = true, error = null))
            try {
                when(val response = UseCase.logout(
                    UserRepository.Token,
                    id_logout
                )) {
                    is NetworkResult.Success -> {

                    }
                    is NetworkResult.Error -> {
                        updateState(state.copy(isLoading = false, error = response.error.message))
                    }
                    is NetworkResult.NoInternet -> {
                        updateState(state.copy(isNotInternet = true))
                    }
                }
                Log.d("logout", state.error.toString())
                UserRepository.act = 0
                UserRepository.UserID = ""
                UserRepository.Token = ""
                UserRepository.pinCode = ""
                UserRepository.notification = true
                UserRepository.email = ""
                controller.navigate(NavigationRoutes.SIGNINPASS)
                Log.d("Success logout", state.isSuccess.toString())
            } catch (e: Exception) {
                Log.e("logout ViewModel", e.message.toString())
            }


        }
    }


    fun downloadPdf(context: Context, pdfUrl: String, fileName: String = "document.pdf") {
        try {
            val request = DownloadManager.Request(Uri.parse(pdfUrl))
                .setTitle("Скачивание PDF")
                .setDescription("Файл скачивается...")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                .setAllowedOverMetered(true) // Скачивать по мобильному интернету
                .setAllowedOverRoaming(true) // Скачивать в роуминге

            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)

            Toast.makeText(context, "Скачивание началось", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}