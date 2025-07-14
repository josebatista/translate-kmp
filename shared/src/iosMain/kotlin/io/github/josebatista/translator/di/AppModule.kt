package io.github.josebatista.translator.di

import io.github.josebatista.translator.database.TranslateDatabase
import io.github.josebatista.translator.translate.data.history.SqlDelightHistoryDataSource
import io.github.josebatista.translator.translate.data.local.DatabaseDriverFactory
import io.github.josebatista.translator.translate.data.remote.HttpClientFactory
import io.github.josebatista.translator.translate.data.translate.KtorTranslateClient
import io.github.josebatista.translator.translate.domain.history.HistoryDataSource
import io.github.josebatista.translator.translate.domain.translate.TranslateClient
import io.github.josebatista.translator.translate.domain.translate.TranslateUseCase

class AppModule {

    val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            TranslateDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    private val translateClient: TranslateClient by lazy {
        KtorTranslateClient(
            HttpClientFactory().create()
        )
    }

    val translateUseCase: TranslateUseCase by lazy {
        TranslateUseCase(translateClient, historyDataSource)
    }

}
