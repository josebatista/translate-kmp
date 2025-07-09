package io.github.josebatista.translator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform