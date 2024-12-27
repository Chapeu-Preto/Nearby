package com.rocketseat.nlw.nearby.core.network

import android.net.http.HttpResponseCache.install
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import kotlinx.serialization.json.Json
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

object KtorHttpClient {
    private const val NETWORK_TIMEOUT = 5_000L

    val httpClientAndroid by lazy {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        useAlternativeNames = true
                        ignoreUnknownKeys = true
                        explicitNulls = true
                        useArrayPolymorphism = true
                        encodeDefaults = false
                    }
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = NETWORK_TIMEOUT
                connectTimeoutMillis = NETWORK_TIMEOUT
                socketTimeoutMillis = NETWORK_TIMEOUT
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
}