package io.github.josebatista.translator.translate.presentation

import io.github.josebatista.translator.core.presentation.UiLanguage
import io.github.josebatista.translator.translate.domain.translate.TranslateError

data class TranslateState(
    val fromText: String = "",
    val toText: String? = null,
    val isTranslating: Boolean = false,
    val fromLanguage: UiLanguage = UiLanguage.byCode("pt"),
    val toLanguage: UiLanguage = UiLanguage.byCode("en"),
    val isChoosingFromLanguage: Boolean = false,
    val isChoosingToLanguage: Boolean = false,
    val error: TranslateError? = null,
    val history: List<UiHistoryItem> = emptyList()
)
