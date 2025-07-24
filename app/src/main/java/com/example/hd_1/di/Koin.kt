package com.example.hd_1.di

import com.example.hd_1.data.GameAPI
import com.example.hd_1.data.GameRepository
import com.example.hd_1.data.GameStorage
import com.example.hd_1.data.InMemoryGameStorage
import com.example.hd_1.data.KtorGameApi
import com.example.hd_1.user_interface.auth.login.LoginViewModel
import com.example.hd_1.user_interface.auth.logup.LogUpViewModel
import com.example.hd_1.user_interface.main.genres.GenresListViewModel
import com.example.hd_1.user_interface.main.platforms.PlatformRowViewModel
import com.example.hd_1.user_interface.main.upcoming.components.UpcomingGamesViewModel
import com.example.hd_1.user_interface.search.SearchListViewModel
import com.example.hd_1.user_interface.game_detail.GameDetailViewModel
import com.example.hd_1.user_interface.game_news_detail.GameNewsDetailViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json {
            ignoreUnknownKeys = true
        }

        HttpClient {
            install(ContentNegotiation) {
                json(json , contentType = ContentType.Any)
            }
        }
    }

    single<GameAPI> {
        KtorGameApi(get())
    }
    single<GameStorage> {
        InMemoryGameStorage()
    }
    single {
        GameRepository(get() , get()).apply {
            initialize()
        }
    }

}

val viewModelModule = module {
    factoryOf(::LoginViewModel)
    factoryOf(::LogUpViewModel)
    factoryOf(::SearchListViewModel)
    factoryOf(::GenresListViewModel)
    factoryOf(::PlatformRowViewModel)
    factoryOf(::UpcomingGamesViewModel)
    factoryOf(::GameDetailViewModel)
    factoryOf(::GameNewsDetailViewModel)
}

fun initKoin() {
    startKoin {
        modules(dataModule , viewModelModule)
    }
}