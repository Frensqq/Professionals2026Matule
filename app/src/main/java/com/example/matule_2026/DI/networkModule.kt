package com.example.matule_2026.DI

import com.example.matule_2026.Domain.UseCase.UseCase
import com.example.matule_2026.Presentation.ViewModels.AuthViewModel
import com.example.matule_2026.Presentation.ViewModels.MainViewModel
import com.example.matule_2026.Presentation.ViewModels.SplashViewModel
import com.example.networklibrary.data.remote.PBApi
import com.example.networklibrary.data.remoute.PBApiServis
import com.example.networklibrary.data.repository.PBRepositoryImpl
import com.example.networklibrary.domain.repository.PBRepository
import com.example.networklibrary.network.monitor.AndroidNetworkMonitor
import com.example.networklibrary.network.monitor.NetworkMonitor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single<NetworkMonitor> { AndroidNetworkMonitor(androidContext()) }

    // PBApi (ваш готовый Retrofit API)
    single<PBApi> { PBApiServis.instance }

    // PBRepository (ваша библиотека)
    single<PBRepository> {
        PBRepositoryImpl(
            api = get<PBApi>(),
            networkMonitor = get<NetworkMonitor>(),
            context = androidContext()
        )
    }

    // UseCase
    factory { UseCase(get()) }

    // ViewModels
    viewModel { AuthViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel() }
}