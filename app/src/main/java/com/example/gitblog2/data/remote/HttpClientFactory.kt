package com.example.gitblog2.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(engine: HttpClientEngine): HttpClient{
        return  HttpClient(engine = engine){
            install(ContentNegotiation){
                json(json = Json { ignoreUnknownKeys = true} ,
                    contentType = io.ktor.http.ContentType.Text.Plain
                )
            }

            install(HttpTimeout) {
                connectTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
            install(Logging){
                level = LogLevel.ALL
                logger = Logger.ANDROID

            }
            defaultRequest {
                contentType(io.ktor.http.ContentType.Application.Any)
            }
        }
    }
}