package io.github.josebatista.translator.core.presentation

import io.github.josebatista.translator.core.domain.language.Language

actual data class UiLanguage(
    actual val language: Language,
    val imageName: String
) {
    actual companion object {

        actual fun byCode(langCode: String): UiLanguage {
            return allLanguages.find { it.language.langCode == langCode }
                ?: throw IllegalArgumentException("Invalid or unsupported language code")
        }

        actual val allLanguages: List<UiLanguage>
            get() = Language.entries.map { language ->
                UiLanguage(
                    language = language,
                    imageName = language.langName.lowercase()
                )
            }
    }
}
