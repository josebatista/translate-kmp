package io.github.josebatista.translator.testing

import io.github.josebatista.translator.core.domain.language.Language
import io.github.josebatista.translator.translate.domain.translate.TranslateClient

class FakeTranslateClient : TranslateClient {

    var translatedText = "test translation"

    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String {
        return translatedText
    }
}
