package io.github.josebatista.translator.di

import io.github.josebatista.translator.database.TranslateDatabase
import io.github.josebatista.translator.translate.data.history.SqlDelightHistoryDataSource
import io.github.josebatista.translator.translate.data.local.DatabaseDriverFactory
import io.github.josebatista.translator.translate.data.remote.HttpClientFactory
import io.github.josebatista.translator.translate.data.translate.KtorTranslateClient
import io.github.josebatista.translator.translate.domain.history.HistoryDataSource
import io.github.josebatista.translator.translate.domain.translate.TranslateClient
import io.github.josebatista.translator.translate.domain.translate.TranslateUseCase
import io.github.josebatista.translator.voicetotext.domain.VoiceToTextParser

interface AppModule {
    val historyDataSource: HistoryDataSource
    val translateClient: TranslateClient
    val translateUseCase: TranslateUseCase
    val voiceParser: VoiceToTextParser
}

class AppModuleImpl(
    parser: VoiceToTextParser
) : AppModule {
    override val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            TranslateDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    override val translateClient: TranslateClient by lazy {
        KtorTranslateClient(
            HttpClientFactory().create()
        )
    }

    override val translateUseCase: TranslateUseCase by lazy {
        TranslateUseCase(translateClient, historyDataSource)
    }

    override val voiceParser: VoiceToTextParser = parser
}
