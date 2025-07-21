package io.github.josebatista.translator.di

import io.github.josebatista.translator.testing.FakeHistoryDataSource
import io.github.josebatista.translator.testing.FakeTranslateClient
import io.github.josebatista.translator.testing.FakeVoiceToTextParser
import io.github.josebatista.translator.translate.domain.history.HistoryDataSource
import io.github.josebatista.translator.translate.domain.translate.TranslateClient
import io.github.josebatista.translator.translate.domain.translate.TranslateUseCase
import io.github.josebatista.translator.voicetotext.domain.VoiceToTextParser

class TestAppModule : AppModule {
    override val historyDataSource: HistoryDataSource = FakeHistoryDataSource()
    override val translateClient: TranslateClient = FakeTranslateClient()
    override val translateUseCase: TranslateUseCase = TranslateUseCase(
        client = translateClient,
        historyDataSource = historyDataSource
    )
    override val voiceParser: VoiceToTextParser = FakeVoiceToTextParser()
}
