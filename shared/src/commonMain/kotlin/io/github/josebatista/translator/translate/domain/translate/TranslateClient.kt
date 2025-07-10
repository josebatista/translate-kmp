package io.github.josebatista.translator.translate.domain.translate

import io.github.josebatista.translator.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language,
    ): String
}
