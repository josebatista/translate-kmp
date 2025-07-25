package io.github.josebatista.translator.core.presentation

import io.github.josebatista.translator.core.domain.language.Language

expect class UiLanguage {
    val language: Language

    companion object {
        fun byCode(langCode: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}
