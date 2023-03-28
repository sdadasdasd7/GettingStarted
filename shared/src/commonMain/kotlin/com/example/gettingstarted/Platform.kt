package com.example.gettingstarted

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform