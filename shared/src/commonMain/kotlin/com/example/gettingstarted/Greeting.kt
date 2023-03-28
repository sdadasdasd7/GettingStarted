package com.example.gettingstarted

import RocketLaunch
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.call.*
import io.ktor.client.request.*

class Greeting {
    private val platform: Platform = getPlatform()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }


    @Throws(Exception::class)
    suspend fun greet(): String {
        val rockets: List<RocketLaunch> =
            httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val lastLaunchSuccess = rockets.last { it.launchSuccess == true }
        return "Hello, ${platform.name.reversed()}!" +
                "\nDays until New Year - ${daysUntilNewYear()}" +
                "\nThe last successful launch was ${lastLaunchSuccess.launchDateUTC}🚀"
    }
}

